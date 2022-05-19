package psk.project.FileRepository.sharedfile.exeptions;

public class ShareLinkBadException extends RuntimeException{
  public ShareLinkBadException(){
    super("Nie znaleziono takiego linku udostępniania!");
  }
}
