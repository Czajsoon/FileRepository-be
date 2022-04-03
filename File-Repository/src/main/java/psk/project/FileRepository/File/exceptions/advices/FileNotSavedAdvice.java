package psk.project.FileRepository.File.exceptions.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;

@ControllerAdvice
public class FileNotSavedAdvice {

    @ResponseBody
    @ExceptionHandler(FileNotSavedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String FileNotSavedHandler(FileNotSavedException ex){
        return ex.getMessage();
    }

}
