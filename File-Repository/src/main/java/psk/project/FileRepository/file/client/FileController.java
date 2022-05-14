package psk.project.FileRepository.file.client;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.file.models.FileCommand;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileResponse;
import psk.project.FileRepository.file.services.FileFacade;

import java.util.UUID;

@RestController
@RequestMapping("file")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class FileController {
    private final FileFacade fileFacade;

    @GetMapping("/{fileId}")
    public FileResponse getFile(@PathVariable String fileId) {
        return fileFacade.getFileInfoById(fileId);
    }

    @GetMapping("/download/{file}")
    public ResponseEntity<Resource> download(@PathVariable String file) {
        return fileFacade.downloadFile(file);
    }

    @PostMapping("/{user}")
    public void postFile(
            @PathVariable String user,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String path
    ) {
        fileFacade.saveFile(FileDTO.builder()
                .file(file)
                .fileName(file.getOriginalFilename())
                .ownerId(UUID.fromString(user))
                .description(description)
                .size(file.getSize())
                .additionalPath(path)
                .build());
    }

    @PatchMapping
    public void editFile(@RequestBody FileCommand command) {
        fileFacade.edit(command);
    }

    @DeleteMapping("/{fileId}")
    public void deleteFile(@PathVariable String fileId){
        fileFacade.deleteFile(fileId);
    }

    @GetMapping("mexicano")
    public ResponseEntity<Resource> mexicano() {
        return fileFacade.mexicanoFile();
    }
}