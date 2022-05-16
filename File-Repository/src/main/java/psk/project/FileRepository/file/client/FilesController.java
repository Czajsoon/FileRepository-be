package psk.project.FileRepository.file.client;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.file.models.FileSearchCommand;
import psk.project.FileRepository.file.services.FileFacade;
import psk.project.FileRepository.models.PageCommand;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("files")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class FilesController {
    private final FileFacade fileFacade;

    @GetMapping(value = "/download/{files}")
    public void download(HttpServletResponse response, @PathVariable List<String> files) {
        fileFacade.downloadFiles(response, files);
    }

    @PostMapping(path = "/{user}")
    public void postFiles(@PathVariable String user,
                          @RequestParam List<MultipartFile> files,
                          @RequestParam(required = false) String description,
                          @RequestParam(required = false) String path) {
        files.forEach(mFile -> fileFacade.saveFile(FileDTO.builder()
                .file(mFile)
                .fileName(mFile.getOriginalFilename())
                .ownerId(UUID.fromString(user))
                .description(description)
                .size(mFile.getSize())
                .additionalPath(path)
                .build()));
    }

    @DeleteMapping("/{fileIds}")
    public void deleteFiles(@PathVariable List<String> fileIds) {
        fileFacade.deleteFiles(fileIds);
    }

    @PostMapping("/{userId}")
    public Map<String,Object> getFilesInDirectory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestBody(required = false) FileSearchCommand command,
            @PathVariable String userId
    ) {
        return fileFacade.getAllUserFiles(
                userId,
                command,
                PageCommand.builder()
                        .page(page)
                        .size(size)
                        .build()
        );
    }



}
