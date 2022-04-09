package psk.project.FileRepository.File.client;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.File.services.FileFacade;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class FileController {
    private final FileFacade fileFacade;

    @GetMapping("file")
    public FileResponse getFile(@RequestParam String fileId) {
        return fileFacade.getFileInfoById(fileId);
    }

    @GetMapping("files")
    public List<FileResponse> getAllFiles(){
        return fileFacade.getAllFiles();
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
}
