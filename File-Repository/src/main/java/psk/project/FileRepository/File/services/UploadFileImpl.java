package psk.project.FileRepository.File.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class UploadFileImpl implements UploadFile {

    @Value("${file.path.source.location}")
    private String path;

    @Override
    public void save(String user, MultipartFile file) throws IOException {
        saveFile(file);
    }

    private void saveFile(MultipartFile file) throws IOException {
        Files.copy(
                file.getInputStream(),
                Path.of(path + file.getOriginalFilename()
                ),StandardCopyOption.REPLACE_EXISTING);
    }
}
