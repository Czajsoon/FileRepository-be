package psk.project.FileRepository.file.dao;

import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileNotSavedException;
import psk.project.FileRepository.file.exceptions.FileTransferNotPossibleException;
import psk.project.FileRepository.file.entity.models.FileDTO;
import psk.project.FileRepository.file.entity.models.FileResponse;
import psk.project.FileRepository.file.entity.models.FileSearchCommand;
import psk.project.FileRepository.file.repository.FileRepository;
import psk.project.FileRepository.models.PageCommand;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static psk.project.FileRepository.utils.StringOperationUtils.getFileExtention;

abstract class FileAbstractDAO {

    protected final FileRepository fileRepository;
    protected final DefaultUserRepository userRepository;

    public final String rootPath;
    private static final String ROOT_FOLDER_NAME = "/Folder główny";

    protected FileAbstractDAO(FileRepository fileRepository, DefaultUserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.rootPath = System.getProperty("user.dir") + "/server/";
    }

    protected List<FileResponse> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(FileResponse::of)
                .toList();
    }

    public File saveInRepository(FileDTO fileDTO,DefaultUser user) {
        updateTransferUser(user,fileDTO.getSize());
        fileDTO.setTotalPath(fileDTO.getPath().substring(rootPath.length()));
        fileDTO.setPath(setFilePath(fileDTO.getAdditionalPath()));
        StringBuilder fileExtension = new StringBuilder(getFileExtention(Objects.requireNonNull(fileDTO.getFile().getOriginalFilename())));
        fileDTO.setFileFormat(fileExtension.deleteCharAt(0).toString().toUpperCase());
        fileDTO.setPureFileName(fileDTO.getFileName().substring(0, fileDTO.getFileName().length() - (fileDTO.getFileFormat().length() + 1)));
        return fileRepository.save(File.of(fileDTO, user));
    }

    protected void checkTransferIsPossible(DefaultUser user, BigInteger size){
        if (user.getPlan().getCapacity().compareTo(user.getTransferUsage().add(size)) < 0){
            throw new FileTransferNotPossibleException();
        }
    }

    private void updateTransferUser(DefaultUser user,BigInteger fileSize){
        //todo error when transfer is not possible
        user.setTransferUsage(user.getTransferUsage().add(fileSize));
        userRepository.save(user);
    }

    protected String setFilePath(String path) {
        if (path.length() == 0)
            return ROOT_FOLDER_NAME;
        else
            return path;
    }

    protected void checkFileExistsOnDirectoryAndGenerateFileNameAndComment(FileDTO fileDTO) {
        boolean fileExistsOnDirectory = fileExistsOnDirectory(fileDTO.getAdditionalPath()
                , fileDTO.getOwnerId()
                , fileDTO.getFile().getOriginalFilename());
        fileDTO.setComment("Brak komentarza...");
        if (fileExistsOnDirectory) {
            fileDTO.setAdditionalFileName(UUID.randomUUID());
            String newFileName = fileDTO.getAdditionalFileName() + fileDTO.getFile().getOriginalFilename();
            fileDTO.setComment(String.format("Zmieniono nazwę pliku:'%s' na:'%s' ponieważ taki juz istnieje w tym folderze!"
                    , fileDTO.getFile().getOriginalFilename()
                    , newFileName));
            fileDTO.setFileName(newFileName);
        }
    }

    protected Path saveFileOnDisc(FileDTO fileDTO) {
        Path path = createDirectoryPath(fileDTO.getAdditionalPath(), fileDTO.getOwnerId());
        createDirectoriesIfNotExists(path);
        return saveFile(path, fileDTO.getFile(), fileDTO.getAdditionalFileName());
    }

    protected boolean fileExistsOnDirectory(String path, String user, String fileName) {
        Path filePath = Path.of(createDirectoryPath(path, user) + "/" + fileName);
        return Files.exists(filePath);
    }

    protected void createDirectoriesIfNotExists(Path path) {
        if (userDirectoryNotExistsInPath(path))
            createUserDirectory(path);
    }

    protected Path createDirectoryPath(String path, String user) {
        String userPath = rootPath;
        if (path.length() != 0) {
            if (path.equals(ROOT_FOLDER_NAME)) userPath += "/" + user + "/";
            else userPath += "/" + user + "/" + path + "/";
        } else userPath += "/" + user + "/";
        return Path.of(userPath);
    }

    private boolean userDirectoryNotExistsInPath(Path path) {
        return !Files.exists(path);
    }

    private void createUserDirectory(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new FileNotSavedException();
        }
    }

    protected String buildFileNameWithExtention(String filename, String newFilename) {
        return newFilename + getFileExtention(filename);
    }

    private Path saveFile(Path path, MultipartFile file, UUID fileUUID) {
        try {
            Path finalPath;
            if (fileUUID != null) {
                finalPath = Path.of(path + "/" + fileUUID + file.getOriginalFilename());
                Files.copy(
                        file.getInputStream(),
                        finalPath,
                        REPLACE_EXISTING);
            } else {
                finalPath = Path.of(path + "/" + file.getOriginalFilename());
                Files.copy(
                        file.getInputStream(),
                        finalPath,
                        REPLACE_EXISTING);
            }
            return finalPath;
        } catch (IOException e) {
            throw new FileNotSavedException();
        }
    }

    protected String getTotalPathFileById(String fileId) {
        File file = fileRepository.findById(UUID.fromString(fileId))
                .orElseThrow(NoSuchElementException::new);
        if (file.getPath().equals(ROOT_FOLDER_NAME))
            return rootPath + "/" + file.getDefaultUser().getDefaultUserID().toString() + "/" + file.getFileName();
        return rootPath + "/" + file.getDefaultUser().getDefaultUserID().toString() + "/" + file.getPath() + "/" + file.getFileName();
    }

    protected Page<File> getAllUserFiles(DefaultUser user, FileSearchCommand searchCommand, PageCommand pageCommand) {
        return fileRepository.findAll(specificationFactory(user, searchCommand), PageRequest.of(pageCommand.getPage(), pageCommand.getSize()));
    }

    private Specification<File> specificationFactory(DefaultUser user, FileSearchCommand searchCommand) {
        return Specification.where(buildSearchCriteriaList(user, searchCommand).build());
    }

    private FileSearchBuilder buildSearchCriteriaList(DefaultUser user, FileSearchCommand command) {
        FileSearchBuilder fileSearchBuilder = new FileSearchBuilder();
        fileSearchBuilder.with("defaultUser", ":", user);
        if (command == null) return fileSearchBuilder;
        if (command.getFileFormat() != null) {
            fileSearchBuilder.with(command.getNameFileFormat(), ":", command.getFileFormat().toUpperCase());
        }
        if (command.getFileName() != null) {
            fileSearchBuilder.with(command.getNameFileName(), ":", command.getFileName());
        }
        if (getResultOfSearchCommandSizeCondition(command)) {
            fileSearchBuilder.with(command.getNameSize(), command.getOperation(), command.getSize());
        }
        if (command.getPath() != null) {
            fileSearchBuilder.with(command.getNamePath(), ":", command.getPath());
        }
        if (command.getDescription() != null) {
            fileSearchBuilder.with(command.getNameDescription(), ":", command.getDescription());
        }
        return fileSearchBuilder;
    }

    private boolean getResultOfSearchCommandSizeCondition(FileSearchCommand command) {
        if (command.getSize() != null && command.getOperation() != null) {
            if (command.getOperation().equals(">") || command.getOperation().equals("<") || command.getOperation().equals(":")) {
                return true;
            }
            command.setOperation(":");
            return true;
        }
        return false;
    }

    @SneakyThrows
    public ResponseEntity<Resource> mexicano() {//no one should see this :D :D :D
        String property = System.getProperty("user.dir") + "/mexicano/mexico.mp4";
        java.io.File file = new java.io.File(property);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            //unknown mimetype so set the mimetype to application/octet-stream
            mimeType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .header("Content-Disposition", "target=\"_blank\"")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(mimeType))
                .body(resource);
    }
}
