package psk.project.FileRepository.File.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.repository.FileRepository;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UploadFileService {
    private final FileRepository fileRepository;
    private final UploadFile uploadFile;
    private final DefaultUserRepository userRpository;

    public UploadFileService(FileRepository fileRepository,
                             UploadFile uploadFile,
                             DefaultUserRepository userRpository
    ) {
        this.fileRepository = fileRepository;
        this.uploadFile = uploadFile;
        this.userRpository = userRpository;
    }

    @Transactional(rollbackFor = {FileNotSavedException.class,UserNotFoundException.class})
    public ResponseEntity<?> uploadFile(FileDTO fileDto) throws FileNotSavedException, UserNotFoundException {
        //TODO dokonczyc wrzucanie plikow na serwer obsługa folderów
        if (fileDto.getAdditionalPath() != null){
            try {
                uploadFile.save(fileDto.getOwnerId(), fileDto.getFile());
                Optional<DefaultUser> user = userRpository.findById(fileDto.getOwnerId());
                if(user.isPresent()){
                    fileRepository.save(File.of(fileDto,user.get()));
                }
                else throw new UserNotFoundException(fileDto.getOwnerId());
            } catch (IOException e) {
                throw new FileNotSavedException();
            }
            return ResponseEntity.ok("File has been uploaded successfully!");
        }
        return null;
    }
}
