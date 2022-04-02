package psk.project.FileRepository.File.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file/")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:filePath.properties")
public class FileController {

    @Value("${file.path.source.location}")
    private String path;

    @GetMapping
    public ResponseEntity<?> getFile(){
        return ResponseEntity.ok().body("Gites! pliki znajdują sie w : " + this.path);
    }

    @PostMapping
    public ResponseEntity<?> postFile(){
        return null;
    }
}
