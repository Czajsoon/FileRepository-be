package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.File.models.FileCommand;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@AllArgsConstructor
public class FileFacade {

    private final UploadFileService uploadFileService;
    private final ResponseFileService responseFileService;
    private final DownloadFileService downloadFileService;
    private final EditFileService editFileService;


    public void saveFile(FileDTO fileDTO) {
        uploadFileService.uploadFile(fileDTO);
    }

    public FileResponse getFileInfoById(String id) {
        return responseFileService.getFileInfoById(id);
    }

    public List<FileResponse> getAllFiles() {
        return responseFileService.getAllFiles();
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

    public List<FileResponse> getAllFormDirectory(String directory,String userId){
        return responseFileService.getFilesInfoByDirectory(directory,userId);
    }

    public List<FileResponse> getAllUserFiles(String userId){
        return responseFileService.getUserFiles(userId);
    }

    public void edit(FileCommand command) {
        editFileService.editFile(command);
    }

    public ResponseEntity<Resource> mexicanoFile() {
        return downloadFileService.mexicanoFilm();
    }
}
