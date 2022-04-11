package psk.project.FileRepository.File.DAO;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.File.repository.FileRepository;

import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileDAO extends AbstractFileDAO implements FDAO<File,FileResponse,FileDTO> {

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
    public List<FileResponse> getAll() {
        return getAllFiles();
    }

    @Override
    @SneakyThrows
    public FileResponse save(FileDTO fileDTO) {
        checkFileExistsOnDirectoryAndGenerateFileNameAndComment(fileDTO);
        Path path = saveFileOnDisc(fileDTO);
        fileDTO.setPath(path.toString());
        File file = saveInRepository(fileDTO);
        return FileResponse.of(file);
    }

    @Override
    public String getTotalPathFile(String id) throws NoSuchElementException{
        return getTotalPathFileById(id);
    }


}
