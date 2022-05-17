package psk.project.FileRepository.file.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserNotFoundException;
import psk.project.FileRepository.file.dao.FileDAO;
import psk.project.FileRepository.file.exceptions.FileNotSavedException;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileResponse;

@Service
@AllArgsConstructor
@Slf4j
class FileUploadService {

    private final FileDAO fileDAO;

    @Transactional(rollbackFor = {FileNotSavedException.class, DefaultUserNotFoundException.class})
    public void uploadFile(FileDTO fileDto) {
        if (fileDto.getAdditionalPath() == null)
            fileDto.setAdditionalPath("");

        FileResponse fileResponse = fileDAO.save(fileDto);
        log.info(String.format("File saved with id:'%s'", fileResponse.getFileId()));
    }


}
