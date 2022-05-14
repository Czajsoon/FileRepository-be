package psk.project.FileRepository.file.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import psk.project.FileRepository.file.dao.FileDAO;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@AllArgsConstructor
@Slf4j
class DownloadFileService {

    private final FileDAO fileDAO;

    @SneakyThrows
    public ResponseEntity<Resource> downloadFile(String fileId) {
        File file;
        InputStreamResource resourceStream;
        try {
            String pathFile = fileDAO.getTotalPathFile(fileId);
            file = new File(pathFile);
            resourceStream = new InputStreamResource(new FileInputStream(file));
        } catch (NoSuchElementException ex) {
            throw new psk.project.FileRepository.file.exceptions.FileNotFoundException(fileId);
        }
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/download; name=\"" + file.getName()
                        + "\""))
                .body(resourceStream);
    }

    public void downloadFiles(List<String> fileIds, HttpServletResponse response) {
        List<String> paths = fileIds.stream()
                .map(fileDAO::getTotalPathFile)
                .toList();
        List<String> files = fileDAO.getAll(fileIds)
                .stream()
                .map(psk.project.FileRepository.file.entity.File::getFileName)
                .toList();


        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=download.zip");
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            int i = 0;
            for (String fileName : paths) {
                i++;
                FileSystemResource fileSystemResource = new FileSystemResource(fileName);
                String name = fileSystemResource.getFilename();
                String finalName = name;
                if (files.stream().anyMatch(fileN -> fileN.equals(finalName))) {
                    name = String.format("(%d)_%s", i, finalName);
                }
                ZipEntry zipEntry = new ZipEntry(name);

                zipEntry.setSize(fileSystemResource.contentLength());
                zipEntry.setTime(System.currentTimeMillis());

                zipOutputStream.putNextEntry(zipEntry);

                StreamUtils.copy(fileSystemResource.getInputStream(), zipOutputStream);
                zipOutputStream.closeEntry();
            }
            zipOutputStream.finish();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public ResponseEntity<Resource> mexicanoFilm() {
        return fileDAO.mexicano();
    }

}
