package psk.project.FileRepository.sharedfile.exeptions;

public class ShareFileLinkBadException extends RuntimeException{
  public ShareFileLinkBadException(){
    super("Nie znaleziono takiego linku udostępniania!");
  }
}
