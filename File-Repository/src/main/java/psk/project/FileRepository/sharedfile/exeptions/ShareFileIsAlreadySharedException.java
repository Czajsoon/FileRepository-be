package psk.project.FileRepository.sharedfile.exeptions;

public class ShareFileIsAlreadySharedException extends RuntimeException{
  public ShareFileIsAlreadySharedException(){
    super("Plik jest juz udostepniony temu uzytkownikowi!");
  }
}
