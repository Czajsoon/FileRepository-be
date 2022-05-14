package psk.project.FileRepository.file.dao;

import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.defaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.defaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileNotSavedException;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileResponse;
import psk.project.FileRepository.file.repository.FileRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

abstract class AbstractFileDAO {

    protected final FileRepository fileRepository;
    protected final DefaultUserRepository userRepository;

    public final String rootPath;

    protected AbstractFileDAO(FileRepository fileRepository, DefaultUserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.rootPath = System.getProperty("user.dir") + "/server/";
    }

    protected List<FileResponse> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(FileResponse::of)
                .toList();
    }

    public File saveInRepository(FileDTO fileDTO) {
        Optional<DefaultUser> user = userRepository.findById(UUID.fromString(fileDTO.getOwnerId()));
        fileDTO.setTotalPath(fileDTO.getPath().substring(rootPath.length()));
        fileDTO.setPath(setFilePath(fileDTO.getAdditionalPath()));
        StringBuilder fileExtension = new StringBuilder(getFileExtention(fileDTO.getFile().getOriginalFilename()));
        fileDTO.setFileFormat(fileExtension.deleteCharAt(0).toString().toUpperCase());
        if (user.isPresent()) {
            return fileRepository.save(File.of(fileDTO, user.get()));
        } else throw new UserNotFoundException(fileDTO.getOwnerId());
    }

    protected String setFilePath(String path) {
        if (path.length() == 0)
            return "/Folder główny";
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
        if(path.length() != 0){
            if(path.equals("/Folder główny"))userPath += "/" + user + "/";
            else userPath += "/" + user + "/" + path + "/";
        }
        else userPath += "/" + user + "/";
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

    protected String buildFileNameWithExtention(String filename,String newFilename){
        return newFilename + getFileExtention(filename);
    }

    private String getFileExtention(String filename) {
        StringBuilder extention = new StringBuilder();
        boolean flag = false;
        for (int i = filename.length() - 1; i > 0; i--) {
            if (flag) break;
            if(filename.charAt(i) == '.') flag = true;
            extention.append(filename.charAt(i));
        }
        extention.reverse();
        return extention.toString();
    }

    private Path saveFile(Path path, MultipartFile file, UUID fileUUID) {
        try {
            Path finalPath;
            if (fileUUID != null) {
                finalPath = Path.of(path + "/" + fileUUID + file.getOriginalFilename());
                Files.copy(
                        file.getInputStream(),
                        finalPath,
                        StandardCopyOption.REPLACE_EXISTING);
            } else {
                finalPath = Path.of(path + "/" + file.getOriginalFilename());
                Files.copy(
                        file.getInputStream(),
                        finalPath,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return finalPath;
        } catch (IOException e) {
            throw new FileNotSavedException();
        }
    }

    protected String getTotalPathFileById(String fileId) {
        File file = fileRepository.findById(UUID.fromString(fileId))
                .orElseThrow(NoSuchElementException::new);
        if(file.getPath().equals("/Folder główny"))
            return rootPath + "/" + file.getDefaultUser().getDefaultUserID().toString() + "/" + file.getFileName();
        return rootPath + "/" + file.getDefaultUser().getDefaultUserID().toString() + "/" + file.getPath() + "/" + file.getFileName();
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
