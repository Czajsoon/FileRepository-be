package psk.project.FileRepository.file.exceptions;

public class FileNotSavedException extends RuntimeException {
    public FileNotSavedException(){
        super("Nie udało się zapisać pliku!");
    }
}
