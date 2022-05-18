package psk.project.FileRepository.defaultuser.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserEditCommand;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserBadPasswordException;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserNotFoundException;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserPhotoException;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.exceptions.FileNotSavedException;
import psk.project.FileRepository.utils.PasswordHash;
import psk.project.FileRepository.utils.StringOperationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@AllArgsConstructor
public class DefaultUserEditService {
    private final DefaultUserRepository defaultUserRepository;

    public void edit(DefaultUserEditCommand command) {
        DefaultUser defaultUser = defaultUserRepository.findById(UUID.fromString(command.getId()))
                .orElseThrow(() -> new DefaultUserNotFoundException(command.getId()));

        if (command.getFile() != null) {
            handlePhotoChange(defaultUser, command.getFile());
        }
        if (command.getLogin() != null) {
            handleLoginChange(defaultUser, command.getLogin());
        }
        if (command.getName() != null) {
            handleNameChange(defaultUser, command.getName());
        }
        if (command.getSurname() != null) {
            handleSurnameChange(defaultUser, command.getSurname());
        }
        if (command.getEmail() != null) {
            handleEmailChange(defaultUser, command.getEmail());
        }
        if (command.getPassword() != null && command.getNewPassword() != null) {
            handlePasswordChange(defaultUser,command.getPassword(),command.getNewPassword());
        }
        defaultUserRepository.save(defaultUser);
    }

    private void handlePasswordChange(DefaultUser user,String password,String newPassword){
        if (PasswordHash.checkPassword(password,user.getPassword())){
            user.setPassword(newPassword);
        }
        else{
            throw new DefaultUserBadPasswordException();
        }
    }

    private void handleEmailChange(DefaultUser user, String email) {
        user.setEmail(email);
    }

    private void handleSurnameChange(DefaultUser user, String surname) {
        user.setSurname(surname);
    }

    private void handleNameChange(DefaultUser user, String name) {
        user.setName(name);
    }

    private void handleLoginChange(DefaultUser user, String login) {
        user.setLogin(login);
    }

    private void handlePhotoChange(DefaultUser user, MultipartFile file) {
        checkPhotoFormat(file);
        String photo = savePhoto(user.getDefaultUserID().toString(), file);
        user.setPhotoLink(photo);
    }

    private String savePhoto(String id, MultipartFile file) {
        Path userPhotoDirectory = Path.of(System.getProperty("user.dir") + "/photos/" + id);
        String fileExtention = StringOperationUtils.getFileExtention(file.getOriginalFilename());
        Path filePath = Path.of(userPhotoDirectory + "/" + id + fileExtention);
        try {
            Path path = Files.createDirectories(userPhotoDirectory);
            Files.copy(
                    file.getInputStream(),
                    Path.of(path + "/" + id + fileExtention),
                    REPLACE_EXISTING
            );
        } catch (IOException e) {
            throw new FileNotSavedException();
        }
        return filePath.toString();
    }

    private void checkPhotoFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (!contentType.equals("image/png"))
            if (!contentType.equals("image/jpeg"))
                throw new DefaultUserPhotoException();
    }
}
