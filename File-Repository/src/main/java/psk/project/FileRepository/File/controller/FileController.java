package psk.project.FileRepository.File.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.services.UploadFile;
import psk.project.FileRepository.File.services.UploadFileService;

import java.io.IOException;

@RestController
@RequestMapping("file/")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:filePath.properties")
@Log4j2
public class FileController {
    private final UploadFileService uploadFileService;

    public FileController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @GetMapping
    public ResponseEntity<?> getFile(){
        return ResponseEntity.ok().body("Gites! pliki znajdują sie w : ");
    }

    @PostMapping(value = {"{user}/{path}" ,"{user}"})
    public ResponseEntity<?> postFile(
            @PathVariable(name = "user") String user,
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "description",required = false) String description,
            @PathVariable(name = "path",required = false) String folders
    ) throws FileNotSavedException, UserNotFoundException {
        return uploadFileService.uploadFile(FileDTO.builder()
                .file(file)
                .ownerId(user)
                .description(description)
                .additionalPath(folders)
                .build());
//        try {
//            uploadFileService.save("123",file);
//            log.atInfo().log("File saved!");
//            return ResponseEntity.ok("File saved!");
//        } catch (IOException e) {
//            log.atWarn().log("File couldn't been saved!");
//            throw new FileNotSavedException();
//        }
    }
}
