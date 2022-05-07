package psk.project.FileRepository.DefaultUser.exceptions;

public class RegisterException extends RuntimeException{

    public RegisterException(){
        super("Rejestracja nie powiodła się");
    }
}
