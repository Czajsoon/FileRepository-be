package psk.project.FileRepository.File.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;

@Service
public class UploadFileService {
    private final UploadFile uploadFile;

    public UploadFileService(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    @Transactional(rollbackFor = {FileNotSavedException.class,UserNotFoundException.class})
    public ResponseEntity<?> uploadFile(FileDTO fileDto) throws FileNotSavedException, UserNotFoundException {
        //TODO dokonczyc wrzucanie plikow na serwer obsługa folderów
        if (fileDto.getAdditionalPath() == null){
            return uploadFile.saveFileInRootFolder(fileDto);
        }
        else return null;
    }


}
