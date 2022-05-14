package psk.project.FileRepository.file.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.project.FileRepository.defaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.file.dao.FileDAO;
import psk.project.FileRepository.file.exceptions.FileNotSavedException;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileResponse;

@Service
@AllArgsConstructor
@Slf4j
class UploadFileService {

    private final FileDAO fileDAO;

    @Transactional(rollbackFor = {FileNotSavedException.class, UserNotFoundException.class})
    public void uploadFile(FileDTO fileDto) {
        if (fileDto.getAdditionalPath() == null)
            fileDto.setAdditionalPath("");

        FileResponse fileResponse = fileDAO.save(fileDto);
        log.info(String.format("File saved with id:'%s'", fileResponse.getFileId()));
    }


}
