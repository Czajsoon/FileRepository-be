package psk.project.FileRepository.sharedfile.exeptions.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import psk.project.FileRepository.sharedfile.exeptions.ShareFileIsAlreadySharedException;
import psk.project.FileRepository.sharedfile.exeptions.ShareFileLinkBadException;
import psk.project.FileRepository.sharedfile.exeptions.ShareFileToSelfException;

@ControllerAdvice
@Slf4j
public class ShareFileControllerAdvice {

  @ResponseBody
  @ExceptionHandler({
      ShareFileToSelfException.class,
      ShareFileLinkBadException.class,
      ShareFileIsAlreadySharedException.class
  })
  @ResponseStatus(HttpStatus.CONFLICT)
  public String conflictAdvice(RuntimeException ex) {
    log.warn(String.format("Error: %s", ex.getMessage()));
    return ex.getMessage();
  }
}
