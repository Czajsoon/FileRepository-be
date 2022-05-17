package psk.project.FileRepository.defaultUser.exceptions;

public class DefaultUserNotFoundException extends RuntimeException{
    public DefaultUserNotFoundException(String userId){
        super("Nie znaleziono uzytkownika z id: " + userId);
    }
}
