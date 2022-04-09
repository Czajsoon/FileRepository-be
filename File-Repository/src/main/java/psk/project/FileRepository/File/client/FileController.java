package psk.project.FileRepository.File.client;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.services.FileFacade;

import java.util.UUID;

@RestController
@RequestMapping("file")
@AllArgsConstructor
public class FileController {
    private final FileFacade fileFacade;

    @GetMapping
    public String getFile() {
        return "Gites! pliki znajdują sie w : ";
    }

    @PostMapping("/{user}")
    public String postFile(
            @PathVariable String user,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String path
    ){
        return fileFacade.saveFile(FileDTO.builder()
                .file(file)
                .ownerId(UUID.fromString(user))
                .description(description)
                .size(file.getSize())
                .additionalPath(path)
                .build());
    }
}
