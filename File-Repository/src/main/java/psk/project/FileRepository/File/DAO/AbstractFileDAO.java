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
import psk.project.FileRepository.File.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@PropertySource("classpath:filePath.properties")
abstract class AbstractFileDAO {

    protected final FileRepository fileRepository;
    protected final DefaultUserRepository userRepository;

    @Value("${file.path.source.location}")
    private String rootPath;

    protected AbstractFileDAO(FileRepository fileRepository, DefaultUserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    public File saveInRepository(FileDTO fileDTO) throws UserNotFoundException {
        Optional<DefaultUser> user = userRepository.findById(UUID.fromString(fileDTO.getOwnerId()));
        if(user.isPresent()){
            return fileRepository.save(File.of(fileDTO,user.get()));
        }
        else throw new UserNotFoundException(fileDTO.getOwnerId());
    }

    public void saveFileOnDisc(FileDTO fileDTO) throws FileNotSavedException {
        Path path = createDirectoryPath(fileDTO.getAdditionalPath(), fileDTO.getOwnerId());
        createDirectoriesIfNotExists(path);
        saveFile(path,fileDTO.getFile());
    }

    private void createDirectoriesIfNotExists(Path path) throws FileNotSavedException {
        if(userDirectoryNotExistsInPath(path))
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
        }
        catch(IOException e){
            throw new FileNotSavedException();
        }
    }

    private void saveFile(Path path, MultipartFile file) throws FileNotSavedException {
        try {
            Files.copy(
                    file.getInputStream(),
                    Path.of(path + "/" + file.getOriginalFilename()
                    ), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e){
            throw new FileNotSavedException();
        }
    }
}
