package psk.project.FileRepository.File.client;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.File.services.FileFacade;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class FileController {
    private final FileFacade fileFacade;

    @GetMapping("fileInfo")
    public FileResponse getFile(@RequestParam String fileId) {
        return fileFacade.getFileInfoById(fileId);
    }

    @GetMapping("filesInfo")
    public List<FileResponse> getAllFiles(){
        return fileFacade.getAllFiles();
    }

    @GetMapping("file/download/{file}")
    public ResponseEntity<Resource> download(@PathVariable String file) {
        return fileFacade.downloadFile(file);
    }

    @GetMapping(value = "files/download/{files}")
    public void download(HttpServletResponse response, @PathVariable List<String> files) {
        fileFacade.downloadFiles(response, files);
    }

    @PostMapping(path = "files/{user}")
    public List<FileResponse> postFiles(@PathVariable String user,
                          @RequestParam List<MultipartFile> files,
                          @RequestParam(required = false) String description,
                          @RequestParam(required = false) String path){
        return files.stream()
                .map(mFile -> fileFacade.saveFile(FileDTO.builder()
                .file(mFile)
                .fileName(mFile.getOriginalFilename())
                .ownerId(UUID.fromString(user))
                .description(description)
                .size(mFile.getSize())
                .additionalPath(path)
                .build()))
                .toList();
    }

    @PostMapping("file/{user}")
    public FileResponse postFile(
            @PathVariable String user,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String path
    ) {
        return fileFacade.saveFile(FileDTO.builder()
                .file(file)
                .fileName(file.getOriginalFilename())
                .ownerId(UUID.fromString(user))
                .description(description)
                .size(file.getSize())
                .additionalPath(path)
                .build());
    }

    @GetMapping("mexicano")
    public ResponseEntity<Resource> mexicano(){
        return fileFacade.mexicanoFile();
    }
}
