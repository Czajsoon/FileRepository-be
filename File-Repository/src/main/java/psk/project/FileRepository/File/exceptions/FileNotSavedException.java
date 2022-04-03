package psk.project.FileRepository.File.exceptions;

public class FileNotSavedException extends Exception {
    public FileNotSavedException(){
        super("File couldn't been saved!");
    }
}
