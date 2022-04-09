package psk.project.FileRepository.File.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.File.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PropertySource("classpath:filePath.properties")
abstract class AbstractFileDAO {

    protected final FileRepository fileRepository;
    protected final DefaultUserRepository userRepository;

    @Value("${file.path.source.location}")
    public String rootPath;

    protected AbstractFileDAO(FileRepository fileRepository, DefaultUserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    protected List<FileResponse> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(FileResponse::of)
                .toList();
    }

    public File saveInRepository(FileDTO fileDTO) throws UserNotFoundException {
        Optional<DefaultUser> user = userRepository.findById(UUID.fromString(fileDTO.getOwnerId()));
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

    protected Path saveFileOnDisc(FileDTO fileDTO) throws FileNotSavedException {
        Path path = createDirectoryPath(fileDTO.getAdditionalPath(), fileDTO.getOwnerId());
        createDirectoriesIfNotExists(path);
        return saveFile(path, fileDTO.getFile(), fileDTO.getAdditionalFileName());
    }

    protected boolean fileExistsOnDirectory(String path, String user, String fileName) {
        Path filePath = Path.of(createDirectoryPath(path, user) + "/" + fileName);
        return Files.exists(filePath);
    }

    private void createDirectoriesIfNotExists(Path path) throws FileNotSavedException {
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

    private void createUserDirectory(Path path) throws FileNotSavedException {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new FileNotSavedException();
        }
    }

    private Path saveFile(Path path, MultipartFile file, UUID fileUUID) throws FileNotSavedException {
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
}
