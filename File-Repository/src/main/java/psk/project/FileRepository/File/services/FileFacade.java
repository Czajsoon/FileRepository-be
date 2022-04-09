package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.File.models.FileDTO;

@Service
@AllArgsConstructor
public class FileFacade {

    private final UploadFileService uploadFileService;

    public String saveFile(FileDTO fileDTO){
        return uploadFileService.uploadFile(fileDTO);
    }
}
