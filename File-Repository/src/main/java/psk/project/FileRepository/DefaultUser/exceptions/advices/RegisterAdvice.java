package psk.project.FileRepository.DefaultUser.exceptions.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import psk.project.FileRepository.DefaultUser.exceptions.RegisterException;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;

@ControllerAdvice
@Slf4j
public class RegisterAdvice {

    @ResponseBody
    @ExceptionHandler(value = RegisterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound(RegisterException ex){
        log.warn(String.format("Register failed with message:'%s'", ex.getMessage()));
        return ex.getMessage();
    }
}