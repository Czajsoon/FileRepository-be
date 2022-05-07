package psk.project.FileRepository.File.DAO;

import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.File.repository.FileRepository;

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
        if (user.isPresent()) {
            return fileRepository.save(File.of(fileDTO, user.get()));
        } else throw new UserNotFoundException(fileDTO.getOwnerId());
    }

    private String setFilePath(String path) {
        if (path.length() == 0)
            return "Folder główny";
        else
            return path;
    }

    protected void checkFileExistsOnDirectoryAndGenerateFileNameAndComment(FileDTO fileDTO){
        boolean fileExistsOnDirectory = fileExistsOnDirectory(fileDTO.getAdditionalPath()
                , fileDTO.getOwnerId()
                , fileDTO.getFile().getOriginalFilename());
        fileDTO.setComment("Brak komentarza...");
        if(fileExistsOnDirectory){
            fileDTO.setAdditionalFileName(UUID.randomUUID());
            String newFileName = fileDTO.getAdditionalFileName() + fileDTO.getFile().getOriginalFilename();
            fileDTO.setComment(String.format("Zmieniono nazwę pliku:'%s' na:'%s' ponieważ taki juz istnieje w tym folderze!"
                    ,fileDTO.getFile().getOriginalFilename()
                    ,newFileName));
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

    private void createDirectoriesIfNotExists(Path path) {
        if (userDirectoryNotExistsInPath(path))
            createUserDirectory(path);
    }

    private Path createDirectoryPath(String path, String user) {
        String userPath = rootPath;
        if (path.length() != 0)
            userPath += "/" + user + "/" + path;
        else
            userPath += "/" + user;

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
        File file = fileRepository.findById(UUID.fromString(fileId)).orElseThrow(NoSuchElementException::new);
        return rootPath + file.getTotalPath();
    }

    @SneakyThrows
    public ResponseEntity<Resource> mexicano(){//no one should see this :D :D :D
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
