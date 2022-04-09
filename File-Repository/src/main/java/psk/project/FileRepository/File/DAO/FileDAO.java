package psk.project.FileRepository.File.DAO;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.File.repository.FileRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FileDAO extends AbstractFileDAO implements FDAO<File,FileResponse,FileDTO> {

    public FileDAO(FileRepository fileRepository, DefaultUserRepository userRepository) {
        super(fileRepository,userRepository);
    }

    @Override
    public Optional<File> get(String id) {
        return fileRepository.findById(UUID.fromString(id));
    }

    @Override
    public List<FileResponse> getAll() {
        return getAllFiles();
    }

    @Override
    @SneakyThrows
    public String save(FileDTO fileDTO) {
        saveFileOnDisc(fileDTO);
        File file = saveInRepository(fileDTO);
        return file.getFileID().toString();
    }

    //TODO sprawdzenie czy plik juz istnieje o takiej nazwie i rozszerzeniu i wyrzucenie wujątku


}
