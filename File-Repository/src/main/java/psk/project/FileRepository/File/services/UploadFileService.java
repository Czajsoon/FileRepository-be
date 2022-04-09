package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.DAO.FileDAO;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;

@Service
@AllArgsConstructor
@Slf4j
public class UploadFileService {

    private final FileDAO fileDAO;

    @Transactional(rollbackFor = {FileNotSavedException.class, UserNotFoundException.class})
    public String uploadFile(FileDTO fileDto) {
        if (fileDto.getAdditionalPath() == null)
            fileDto.setAdditionalPath("");
        try {
            fileDAO.save(fileDto);
        } catch (FileNotSavedException | UserNotFoundException e) {
            log.warn(String.format("Saving file failed with message:'%s'", e.getMessage()));
        }
        return "File uploaded successfully!!";
    }
}
