package psk.project.FileRepository.defaultUser.exceptions;

public class DefaultUserWrongAuthorizationDataException extends RuntimeException{

    public DefaultUserWrongAuthorizationDataException(){
        super("Niepoprawny login lub haslo.");
    }
}
