package psk.project.FileRepository.DefaultUser.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String userId){
        super("User not found with id: " + userId);
    }
}
