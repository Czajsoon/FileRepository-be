package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class FileFacade {

    private final UploadFileService uploadFileService;
    private final ResponseFileService responseFileService;

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
}
