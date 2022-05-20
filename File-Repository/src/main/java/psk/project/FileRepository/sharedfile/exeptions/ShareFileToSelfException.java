package psk.project.FileRepository.sharedfile.exeptions;

public class ShareFileToSelfException extends RuntimeException{
  public ShareFileToSelfException(){
    super("Nie mozna podac wlasnego linku udostepniania");
  }
}
