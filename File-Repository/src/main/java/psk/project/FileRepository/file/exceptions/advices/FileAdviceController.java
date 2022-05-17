package psk.project.FileRepository.file.exceptions.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import psk.project.FileRepository.file.exceptions.*;

@ControllerAdvice
@EnableWebMvc
@Slf4j
public class FileAdviceController {

    @ResponseBody
    @ExceptionHandler({
            FileNotSavedException.class,
            FileExistsOnDirectoryException.class,
            FileTransferNotPossibleException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictAdvice(RuntimeException ex){
        log.warn(String.format("Error: %s", ex.getMessage()));
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({
            FileNotFoundException.class,
            FilesNoOnDirectory.class,
            FileChangeNameException.class,
            FileChangeDirectoryException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noyFoundAdvice(RuntimeException ex){
        log.warn(String.format("Error: '%s'", ex.getMessage()));
        return ex.getMessage();
    }
}
