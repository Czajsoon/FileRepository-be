package psk.project.FileRepository.File.exceptions;

public class FileChangeDirectoryException extends RuntimeException{
    public FileChangeDirectoryException(){
        super("Wystąpił błąd podczas zmiany folderu!");
    }
}
