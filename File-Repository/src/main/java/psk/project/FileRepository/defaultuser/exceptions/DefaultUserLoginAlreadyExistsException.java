package psk.project.FileRepository.defaultuser.exceptions;

public class DefaultUserLoginAlreadyExistsException extends RuntimeException{
    public DefaultUserLoginAlreadyExistsException(){
        super("Wprowadzony login jest juz zajety wybierz inny!");
    }
}
