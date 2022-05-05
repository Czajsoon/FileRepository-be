package psk.project.FileRepository.DefaultUser.exceptions;

public class WrongAuthorizationDataException extends RuntimeException{

    public WrongAuthorizationDataException(){
        super("Wrong Login or Password");
    }
}
