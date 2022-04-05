package psk.project.FileRepository.File.services;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;

import java.io.IOException;

public interface UploadFile {
    void save(String user, MultipartFile file) throws IOException;
    ResponseEntity<?> saveFileInRootFolder(FileDTO fileDto) throws UserNotFoundException, FileNotSavedException;
}
