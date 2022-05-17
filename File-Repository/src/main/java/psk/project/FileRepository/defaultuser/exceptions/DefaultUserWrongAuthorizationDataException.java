package psk.project.FileRepository.defaultuser.exceptions;

public class DefaultUserWrongAuthorizationDataException extends RuntimeException{

    public DefaultUserWrongAuthorizationDataException(){
        super("Niepoprawny login lub haslo.");
    }
}
