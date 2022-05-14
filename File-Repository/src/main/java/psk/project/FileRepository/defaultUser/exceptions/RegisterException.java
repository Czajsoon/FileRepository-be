package psk.project.FileRepository.defaultUser.exceptions;

public class RegisterException extends RuntimeException{

    public RegisterException(){
        super("Rejestracja nie powiodła się");
    }
}
