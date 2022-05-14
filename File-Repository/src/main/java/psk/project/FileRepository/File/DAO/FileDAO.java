package psk.project.FileRepository.File.DAO;

import org.springframework.stereotype.Component;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.exceptions.FileChangeDirectoryException;
import psk.project.FileRepository.File.exceptions.FileChangeNameException;
import psk.project.FileRepository.File.exceptions.FileExistsOnDirectoryException;
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

@Component
public class FileDAO extends AbstractFileDAO implements FileDAOInterface<File, FileResponse, FileDTO> {

    public FileDAO(FileRepository fileRepository, DefaultUserRepository userRepository) {
        super(fileRepository, userRepository);
    }

    @Override
    public Optional<File> get(String id) {
        return fileRepository.findById(UUID.fromString(id));
    }


    @Override
    public List<File> getAll(List<String> idList) {
        return fileRepository.findAllById(idList.stream().map(UUID::fromString).toList());
    }

    @Override
    public List<File> getAllByUser(String userId) {
        DefaultUser user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
        return fileRepository.findAllByDefaultUser(user);
    }

    @Override
    public List<File> getAllByPath(String path,String userId) {
        DefaultUser user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
        return fileRepository.findAllByPathAndDefaultUser(path,user);
    }

    @Override
    public List<FileResponse> getAll() {
        return getAllFiles();
    }

    @Override
    public FileResponse save(FileDTO fileDTO) {
        checkFileExistsOnDirectoryAndGenerateFileNameAndComment(fileDTO);
        Path path = saveFileOnDisc(fileDTO);
        fileDTO.setPath(path.toString());
        File file = saveInRepository(fileDTO);
        return FileResponse.of(file);
    }

    @Override
    public void changePathFile(File file, String path) {
        String user = file.getDefaultUser().getDefaultUserID().toString();
        Path filePath = createDirectoryPath(path, user);
        Path source = Path.of(createDirectoryPath(file.getPath(), user) + "/" + file.getFileName());
        createDirectoriesIfNotExists(filePath);
        if (fileExistsOnDirectory(path, file.getDefaultUser().getDefaultUserID().toString(), file.getFileName()))
            throw new FileExistsOnDirectoryException(file.getFileName());
        try {
            Files.move(
                    source,
                    Path.of(filePath + "/" + file.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING
            );
            file.setPath("/" + setFilePath(path));
            file.setTotalPath(filePath.toString());
        } catch (IOException e) {
            throw new FileChangeDirectoryException();
        }
    }

    @Override
    public void changeFileName(File file, String fileName) {
        Path source = createDirectoryPath(file.getPath(), file.getDefaultUser().getDefaultUserID().toString());
        Path sourcePath = Path.of(source + "/" + file.getFileName());
        String newFilename = buildFileNameWithExtention(file.getFileName(), fileName);
        try {
            Files.move(sourcePath, sourcePath.resolveSibling(newFilename), StandardCopyOption.REPLACE_EXISTING);
            file.setFileName(newFilename);
        } catch (IOException e) {
            throw new FileChangeNameException();
        }

    }

    @Override
    public String getTotalPathFile(String id) {
        return getTotalPathFileById(id);
    }

    @Override
    public void deleteFile(File file) {
        String totalPathFile = getTotalPathFile(file.getFileID().toString());
        try {
            Files.delete(Path.of(totalPathFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileRepository.delete(file);
    }


}
