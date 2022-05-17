package psk.project.FileRepository.file.exceptions;

public class FileTransferNotPossibleException extends RuntimeException{
    public FileTransferNotPossibleException(){
        super("Twoj plik nie moze zostac wrzucony poniewaz posiadasz zbyt malo miejsca na dysku. Aby powiekszyc ilosc miejsca wykupi inny pakiet.");
    }
}
