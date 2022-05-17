package psk.project.FileRepository.defaultuser.exceptions;

public class DefaultUserRegisterException extends RuntimeException{

    public DefaultUserRegisterException(){
        super("Rejestracja nie powiodła się");
    }
}
