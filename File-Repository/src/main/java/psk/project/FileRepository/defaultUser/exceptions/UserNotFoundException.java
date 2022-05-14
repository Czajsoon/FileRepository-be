package psk.project.FileRepository.defaultUser.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId){
        super("User not found with id: " + userId);
    }
}
