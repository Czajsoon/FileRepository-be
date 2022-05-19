package psk.project.FileRepository.file.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.file.dao.FileDAO;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileErrorInputStreamException;
import psk.project.FileRepository.file.exceptions.FileNotFoundException;
import psk.project.FileRepository.file.entity.models.FileResponse;
import psk.project.FileRepository.file.entity.models.FileSearchCommand;
import psk.project.FileRepository.models.PageCommand;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
class FileResponseService {

    private final FileDAO fileDAO;

    public FileResponse getFileInfoById(String id) {
        Optional<File> file = fileDAO.get(id);
        if (file.isPresent())
            return FileResponse.of(file.get());
        throw new FileNotFoundException(id);
    }

    public byte[] getFilePreview(String fileId){
        return fileDAO.getInputFileStream(fileId)
            .orElseThrow(FileErrorInputStreamException::new);
    }

    public Map<String, Object> getUserFiles(String userId, FileSearchCommand searchCommand, PageCommand pageCommand) {
        Page<File> allByUser = fileDAO.getAllByUser(userId, searchCommand, pageCommand);
        return Map.ofEntries(
                Map.entry(pageCommand.getNameTotalRecords(), allByUser.getTotalElements()),
                Map.entry(pageCommand.getNameFiles(), allByUser.getContent().stream().map(FileResponse::of).toList()),
                Map.entry(pageCommand.getNameTotalPages(), allByUser.getTotalPages())
        );
    }

    public List<FileResponse> getAllFiles() {
        return fileDAO.getAll();
    }
}
