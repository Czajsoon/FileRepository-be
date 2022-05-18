package psk.project.FileRepository.defaultuser.exceptions;

public class DefaultUserBadPasswordException extends RuntimeException{
    public DefaultUserBadPasswordException(){
        super("Wpisane haslo nie pasuje do aktualnego hasla");
    }
}
