package psk.project.FileRepository.file.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.defaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.defaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileChangeDirectoryException;
import psk.project.FileRepository.file.exceptions.FileChangeNameException;
import psk.project.FileRepository.file.exceptions.FileExistsOnDirectoryException;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileResponse;
import psk.project.FileRepository.file.repository.FileRepository;
import psk.project.FileRepository.models.PageCommand;

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
    public Page<File> getAllByUser(String userId, PageCommand command) {
        DefaultUser user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
        return fileRepository.findAllByDefaultUser(user, PageRequest.of(command.getPage(),command.getSize()));
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
