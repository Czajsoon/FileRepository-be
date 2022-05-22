package psk.project.FileRepository.sharedfile.exeptions;

public class ShareFileNotRemovedException extends RuntimeException{
    public ShareFileNotRemovedException(){
        super("Cos poszlo nie tak. Sprobuj jeszcze raz.");
    }
}
