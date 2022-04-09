package psk.project.FileRepository.File.exceptions.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;

@ControllerAdvice
@EnableWebMvc
@Slf4j
public class FileNotSavedAdvice {

    @ResponseBody
    @ExceptionHandler(FileNotSavedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String FileNotSavedHandler(FileNotSavedException ex){
        log.warn(String.format("Saving file failed with message:'%s'", ex.getMessage()));
        return ex.getMessage();
    }

}
