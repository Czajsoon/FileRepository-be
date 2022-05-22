package psk.project.FileRepository.defaultuser.exceptions;

public class DefaultUserImageNotFoundException extends RuntimeException{
  public DefaultUserImageNotFoundException(){
    super("Awatar uzytkownika nie zostal znaleziony!");
  }
}
