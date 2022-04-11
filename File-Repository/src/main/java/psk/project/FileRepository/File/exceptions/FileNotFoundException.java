package psk.project.FileRepository.File.exceptions;


public class FileNotFoundException extends Exception{
    public FileNotFoundException(String id){
        super(String.format("The file was not been found with id:'%s'. Skontaktuj się z administartorem!",id));
    }
}
