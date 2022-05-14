package psk.project.FileRepository.file.exceptions;

public class FileExistsOnDirectoryException extends RuntimeException{
    public FileExistsOnDirectoryException(String fileName){
        super(String.format("Plik o nazwie %s isteniej w tym folderze wybierz inna nazwe.",fileName));
    }
}
