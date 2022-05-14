package psk.project.FileRepository.defaultUser.exceptions.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import psk.project.FileRepository.defaultUser.exceptions.UserNotFoundException;

@ControllerAdvice
@Slf4j
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFound(UserNotFoundException ex){
        log.warn(String.format("Saving file failed with message:'%s'", ex.getMessage()));
        return ex.getMessage();
    }
}
