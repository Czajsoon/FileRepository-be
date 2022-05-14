package psk.project.FileRepository.File.exceptions;

public class FileNotSavedException extends RuntimeException {
    public FileNotSavedException(){
        super("Nie udało się zapisać pliku!");
    }
}
