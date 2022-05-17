package psk.project.FileRepository.file.services;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.file.models.FileCommand;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileResponse;
import psk.project.FileRepository.file.models.FileSearchCommand;
import psk.project.FileRepository.models.PageCommand;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FileFacade {

    private final FileUploadService uploadFileService;
    private final FileResponseService responseFileService;
    private final FileDownloadService downloadFileService;
    private final FileEditService editFileService;


    public void saveFile(FileDTO fileDTO) {
        uploadFileService.uploadFile(fileDTO);
    }

    public FileResponse getFileInfoById(String id) {
        return responseFileService.getFileInfoById(id);
    }

    public ResponseEntity<Resource> downloadFile(String id) {
        return downloadFileService.downloadFile(id);
    }

    public void downloadFiles(HttpServletResponse response, List<String> ids) {
        downloadFileService.downloadFiles(ids, response);
    }

    public void deleteFile(String fileId) {
        editFileService.deleteFile(fileId);
    }

    public void deleteFiles(List<String> fileIds) {
        fileIds.forEach(this::deleteFile);
    }

    public Map<String, Object> getAllUserFiles(String userId, FileSearchCommand searchCommand, PageCommand pageCommand) {
        return responseFileService.getUserFiles(userId, searchCommand, pageCommand);
    }

    public void edit(FileCommand command) {
        editFileService.editFile(command);
    }

    public ResponseEntity<Resource> mexicanoFile() {
        return downloadFileService.mexicanoFilm();
    }
}
