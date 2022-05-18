package psk.project.FileRepository.defaultuser.exceptions;

public class DefaultUserPhotoException extends RuntimeException{
    public DefaultUserPhotoException(){
        super("Plik nie jest formatu .png lub .jpg!");
    }
}
