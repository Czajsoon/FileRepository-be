package psk.project.FileRepository.file.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.file.dao.FileDAO;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileNotFoundException;
import psk.project.FileRepository.file.models.FileCommand;
import psk.project.FileRepository.file.repository.FileRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EditFileService {

    private final FileRepository fileRepository;
    private final FileDAO fileDAO;

    public void editFile(FileCommand command) {
        File file = fileRepository.findById(UUID.fromString(command.getFileId()))
                .orElseThrow(() -> new FileNotFoundException(command.getFileId()));
        if(command.getPath() != null){
            handleChangeFilePath(file, command.getPath());
        }
        if (command.getFileName() != null){
            handleChangeFileName(file, command.getFileName());
        }
        if(command.getDescription() != null){
            handleDescriptionChange(file, command.getDescription());
        }
        fileRepository.save(file);
    }

    public void deleteFile(String fileId){
        File file = fileRepository.findById(UUID.fromString(fileId))
                .orElseThrow(() -> new FileNotFoundException(fileId));
        fileDAO.deleteFile(file);
    }

    private void handleDescriptionChange(File file,String description){
        file.setDescription(description);
    }

    private void handleChangeFileName(File file, String fileName){
        fileDAO.changeFileName(file,fileName);
        fileRepository.save(file);
    }

    private void handleChangeFilePath(File file, String path){
        fileDAO.changePathFile(file, path);
        fileRepository.save(file);
    }
}
