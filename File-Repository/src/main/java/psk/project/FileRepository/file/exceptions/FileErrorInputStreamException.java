package psk.project.FileRepository.file.exceptions;

public class FileErrorInputStreamException extends RuntimeException{
  public FileErrorInputStreamException(){
    super("Nastąpil blad podczas zwracania widoku pliku!");
  }
}
