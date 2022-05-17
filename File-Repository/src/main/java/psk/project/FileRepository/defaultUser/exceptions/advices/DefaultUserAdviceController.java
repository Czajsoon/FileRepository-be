package psk.project.FileRepository.defaultUser.exceptions.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import psk.project.FileRepository.defaultUser.exceptions.DefaultUserLoginAlreadyExistsException;
import psk.project.FileRepository.defaultUser.exceptions.DefaultUserNotFoundException;
import psk.project.FileRepository.defaultUser.exceptions.DefaultUserRegisterException;
import psk.project.FileRepository.defaultUser.exceptions.DefaultUserWrongAuthorizationDataException;

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
            DefaultUserLoginAlreadyExistsException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictAdvice(RuntimeException ex) {
        log.warn(String.format("Error:'%s'", ex.getMessage()));
        return ex.getMessage();
    }

}
