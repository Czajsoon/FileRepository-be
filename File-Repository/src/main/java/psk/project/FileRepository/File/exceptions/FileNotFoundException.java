package psk.project.FileRepository.File.exceptions;


public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String id) {
        super(String.format("Plik o id:'%s' nie zostal znaleziony. Skontaktuj sie z administartorem!", id));
    }
}
