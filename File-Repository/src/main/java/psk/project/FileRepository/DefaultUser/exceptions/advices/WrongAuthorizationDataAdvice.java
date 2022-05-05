package psk.project.FileRepository.DefaultUser.exceptions.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import psk.project.FileRepository.DefaultUser.exceptions.WrongAuthorizationDataException;


@ControllerAdvice
@Slf4j
public class WrongAuthorizationDataAdvice {

    @ResponseBody
    @ExceptionHandler(value= WrongAuthorizationDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound(WrongAuthorizationDataException ex){
        log.warn(String.format("Login failed:'%s'", ex.getMessage()));
        return ex.getMessage();
    }
}
