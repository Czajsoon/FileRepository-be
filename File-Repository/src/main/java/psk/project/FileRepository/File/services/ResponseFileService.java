package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.File.DAO.FileDAO;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.exceptions.FileNotFoundException;
import psk.project.FileRepository.File.models.FileResponse;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class ResponseFileService {

    private final FileDAO fileDAO;

    public FileResponse getFileInfoById(String id) {
        Optional<File> file = fileDAO.get(id);
        if (file.isPresent())
            return FileResponse.of(file.get());
        throw new FileNotFoundException(id);
    }

    public List<FileResponse> getAllFiles() {
        return fileDAO.getAll();
    }
}
