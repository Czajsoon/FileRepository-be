package psk.project.FileRepository.file.exceptions;

public class FileChangeNameException extends RuntimeException{
    public FileChangeNameException(){
        super("Wystąpił błąd podczas zmiany nazwy pliku!");
    }
}
