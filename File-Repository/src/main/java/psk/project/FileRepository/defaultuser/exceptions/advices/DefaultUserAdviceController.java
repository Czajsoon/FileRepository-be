package psk.project.FileRepository.defaultuser.exceptions.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import psk.project.FileRepository.defaultuser.exceptions.*;

@ControllerAdvice
@Slf4j
public class DefaultUserAdviceController {

    @ResponseBody
    @ExceptionHandler(value = {
            DefaultUserNotFoundException.class,
            DefaultUserWrongAuthorizationDataException.class,
            DefaultUserRegisterException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = {
            DefaultUserLoginAlreadyExistsException.class,
            DefaultUserPhotoException.class,
            DefaultUserBadPasswordException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }

}
