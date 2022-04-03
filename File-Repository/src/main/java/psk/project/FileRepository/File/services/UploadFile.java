package psk.project.FileRepository.File.services;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFile {
    void save(String user, MultipartFile file) throws IOException;
}
