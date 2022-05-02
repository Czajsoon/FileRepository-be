package psk.project.FileRepository.File.exceptions;

public class FileNotSavedException extends RuntimeException {
    public FileNotSavedException(){
        super("File couldn't been saved!");
    }
}
