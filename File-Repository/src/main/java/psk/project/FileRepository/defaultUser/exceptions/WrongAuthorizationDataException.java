package psk.project.FileRepository.defaultUser.exceptions;

public class WrongAuthorizationDataException extends RuntimeException{

    public WrongAuthorizationDataException(){
        super("Wrong Login or Password");
    }
}
