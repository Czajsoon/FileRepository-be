package psk.project.FileRepository.File.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.File.repository.FileRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UploadFileImpl implements UploadFile {

    private final FileRepository fileRepository;
    private final DefaultUserRepository userRepository;

    @Value("${file.path.source.location}")
    private String path;

    @Override
    public void save(String user, MultipartFile file) throws IOException {
        Files.copy(
                file.getInputStream(),
                Path.of(path + file.getOriginalFilename()
                ),StandardCopyOption.REPLACE_EXISTING);
    }

    public ResponseEntity<?> saveFileInRootFolder(FileDTO fileDto) throws UserNotFoundException,FileNotSavedException{
        try {
            //TODO implement size of file to repo and define root folder for user
            save(fileDto.getOwnerId(), fileDto.getFile());
            Optional<DefaultUser> user = userRepository.findById(UUID.fromString(fileDto.getOwnerId()));
            if(user.isPresent()){
                fileRepository.save(File.of(fileDto,user.get()));
            }
            else throw new UserNotFoundException(fileDto.getOwnerId());
        } catch (IOException | NoSuchElementException e) {
            throw new FileNotSavedException();
        }
        return ResponseEntity.ok("File has been uploaded successfully!");
    }
}
