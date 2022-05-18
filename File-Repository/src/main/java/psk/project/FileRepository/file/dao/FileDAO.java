package psk.project.FileRepository.file.dao;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserNotFoundException;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileChangeDirectoryException;
import psk.project.FileRepository.file.exceptions.FileChangeNameException;
import psk.project.FileRepository.file.exceptions.FileExistsOnDirectoryException;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileResponse;
import psk.project.FileRepository.file.models.FileSearchCommand;
import psk.project.FileRepository.file.repository.FileRepository;
import psk.project.FileRepository.models.PageCommand;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileDAO extends FileAbstractDAO implements FileDAOInterface<File, FileResponse, FileDTO> {

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
    public Page<File> getAllByUser(String userId, FileSearchCommand searchCommand, PageCommand pageCommand) {
        DefaultUser user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new DefaultUserNotFoundException(userId));
        return getAllUserFiles(user, searchCommand, pageCommand);
    }

    @Override
    public List<FileResponse> getAll() {
        return getAllFiles();
    }

    @Override
    public FileResponse save(FileDTO fileDTO) {
        DefaultUser user = userRepository.findById(UUID.fromString(fileDTO.getOwnerId()))
                .orElseThrow(() -> new DefaultUserNotFoundException(fileDTO.getOwnerId()));
        checkTransferIsPossible(user, BigInteger.valueOf(fileDTO.getFile().getSize()));
        checkFileExistsOnDirectoryAndGenerateFileNameAndComment(fileDTO);
        Path path = saveFileOnDisc(fileDTO);
        fileDTO.setPath(path.toString());
        File file = saveInRepository(fileDTO,user);
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

        //dopisane na szybkosci

        DefaultUser user = userRepository
                .findById(file.getDefaultUser().getDefaultUserID())
                .orElseThrow(() -> new DefaultUserNotFoundException(file.getDefaultUser().getDefaultUserID().toString()));

        user.setTransferUsage(user.getTransferUsage().subtract(file.getSize()));
        userRepository.save(user);
    }


}
