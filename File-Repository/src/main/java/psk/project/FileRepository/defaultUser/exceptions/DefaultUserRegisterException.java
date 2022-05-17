package psk.project.FileRepository.defaultUser.exceptions;

public class DefaultUserRegisterException extends RuntimeException{

    public DefaultUserRegisterException(){
        super("Rejestracja nie powiodła się");
    }
}
