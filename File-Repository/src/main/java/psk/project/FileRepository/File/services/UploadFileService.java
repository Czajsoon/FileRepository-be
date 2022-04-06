package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;

@Service
@AllArgsConstructor
public class UploadFileService {
    private final UploadFile uploadFile;

    @Transactional(rollbackFor = {FileNotSavedException.class,UserNotFoundException.class})
    public ResponseEntity<?> uploadFile(FileDTO fileDto) throws FileNotSavedException, UserNotFoundException {
        //TODO dokonczyc wrzucanie plikow na serwer obsługa folderów
        if (fileDto.getAdditionalPath() == null){
            return uploadFile.saveFileInRootFolder(fileDto);
        }
        else return null;
    }


}
