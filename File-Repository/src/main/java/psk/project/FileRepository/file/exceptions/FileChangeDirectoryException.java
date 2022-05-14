package psk.project.FileRepository.file.exceptions;

public class FileChangeDirectoryException extends RuntimeException{
    public FileChangeDirectoryException(){
        super("Wystąpił błąd podczas zmiany folderu!");
    }
}
