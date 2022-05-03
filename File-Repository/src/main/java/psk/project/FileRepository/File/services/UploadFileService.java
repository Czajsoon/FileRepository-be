package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.DAO.FileDAO;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;

@Service
@AllArgsConstructor
@Slf4j
class UploadFileService {

    private final FileDAO fileDAO;

    @Transactional(rollbackFor = {FileNotSavedException.class, UserNotFoundException.class})
    public FileResponse uploadFile(FileDTO fileDto) {
        if (fileDto.getAdditionalPath() == null)
            fileDto.setAdditionalPath("");

        FileResponse fileResponse = fileDAO.save(fileDto);
        log.info(String.format("File saved with id:'%s'", fileResponse.getFileId()));
        return fileResponse;
    }


}
