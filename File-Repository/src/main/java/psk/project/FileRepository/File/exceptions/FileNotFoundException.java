package psk.project.FileRepository.File.exceptions;


public class FileNotFoundException extends Exception{
    public FileNotFoundException(){
        super("The file was not been saved!");
    }
}
