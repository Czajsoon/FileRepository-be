package psk.project.FileRepository.file.exceptions;

public class FilesNoOnDirectory extends RuntimeException {
    public FilesNoOnDirectory(String directory) {
        super(String.format("Nie ma plików w folderze: %s!", directory));
    }
}
