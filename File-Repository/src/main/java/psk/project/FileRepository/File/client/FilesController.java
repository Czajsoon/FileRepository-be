package psk.project.FileRepository.File.client;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.models.FileResponse;
import psk.project.FileRepository.File.services.FileFacade;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("files")
@CrossOrigin("localhost:4200")
@AllArgsConstructor
public class FilesController {
    private final FileFacade fileFacade;

    @GetMapping(value = "/download/{files}")
    public void download(HttpServletResponse response, @PathVariable List<String> files) {
        fileFacade.downloadFiles(response, files);
    }

//    @GetMapping("info")
//    public List<FileResponse> getAllFiles() {
//        return fileFacade.getAllFiles();
//    }

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

    @GetMapping("/{userId}")
    public List<FileResponse> getFilesInDirectory(
            @RequestParam(required = false) String directory,
            @PathVariable String userId
    ) {
        if(directory == null) return fileFacade.getAllUserFiles(userId);
        else return fileFacade.getAllFormDirectory(directory, userId);
    }

}
