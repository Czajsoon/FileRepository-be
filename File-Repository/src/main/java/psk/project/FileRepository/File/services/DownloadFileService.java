package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.File.DAO.FileDAO;

import java.io.File;
import java.io.FileInputStream;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DownloadFileService {

    private final FileDAO fileDAO;

    @SneakyThrows
    public ResponseEntity<Resource> downloadFile(String fileId) {
        File file;
        InputStreamResource resourceStream;
        try {
            String pathFile = fileDAO.getTotalPathFile(fileId);
            file = new File(pathFile);
            resourceStream = new InputStreamResource(new FileInputStream(file));
        }
        catch (NoSuchElementException ex){
            throw new psk.project.FileRepository.File.exceptions.FileNotFoundException(fileId);
        }
        return ResponseEntity.ok()
                .header("Content-Disposition","inline; filename=\"" + file.getName() + "\"")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/download; name=\"" + file.getName()
                        + "\""))
                .body(resourceStream);
    }

    public ResponseEntity<Resource> mexicanoFilm(){
        return fileDAO.mexicano();
    }

}
