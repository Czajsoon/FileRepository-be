package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    @SneakyThrows
    public FileResponse saveFile(FileDTO fileDTO) {
        return uploadFileService.uploadFile(fileDTO);
    }

    public FileResponse getFileInfoById(String id){
        return responseFileService.getFileInfoById(id);
    }

    public List<FileResponse> getAllFiles(){
        return responseFileService.getAllFiles();
    }

    public ResponseEntity<Resource> downloadFile(String id) {
        return downloadFileService.downloadFile(id);
    }

    public void downloadFiles(HttpServletResponse response, List<String> ids) {
        downloadFileService.downloadFiles(ids,response);
    }

    public ResponseEntity<Resource> mexicanoFile(){
        return downloadFileService.mexicanoFilm();
    }
}
