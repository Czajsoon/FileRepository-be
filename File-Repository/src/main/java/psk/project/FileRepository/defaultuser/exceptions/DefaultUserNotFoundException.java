package psk.project.FileRepository.defaultuser.exceptions;

public class DefaultUserNotFoundException extends RuntimeException{
    public DefaultUserNotFoundException(String userId){
        super("Nie znaleziono uzytkownika z id: " + userId);
    }
}
