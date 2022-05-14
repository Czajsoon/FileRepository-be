package psk.project.FileRepository.file.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.file.dao.FileDAO;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileNotFoundException;
import psk.project.FileRepository.file.exceptions.FilesNoOnDirectory;
import psk.project.FileRepository.file.models.FileResponse;
import psk.project.FileRepository.models.PageCommand;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<FileResponse> getFilesInfoByDirectory(String directory, String userId) {
        List<File> allByPath = fileDAO.getAllByPath(directory, userId);
        if (allByPath.isEmpty()) throw new FilesNoOnDirectory(directory);
        return allByPath.stream().map(FileResponse::of).collect(Collectors.toList());
    }

    public Map<String,Object> getUserFiles(String userId, PageCommand command) {
        Page<File> allByUser = fileDAO.getAllByUser(userId,command);
        return Map.ofEntries(
                Map.entry(command.getNameTotalRecords(), allByUser.getTotalElements()),
                Map.entry(command.getNameFiles(), allByUser.getContent().stream().map(FileResponse::of).toList()),
                Map.entry(command.getNameTotalPages(), allByUser.getTotalPages())
        );
    }

    public List<FileResponse> getAllFiles() {
        return fileDAO.getAll();
    }
}
