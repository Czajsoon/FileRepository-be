package psk.project.FileRepository.defaultuser.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserEditCommand;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.defaultuser.services.DefaultUserFacade;

import java.util.List;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class DefaultUserController {
    private final DefaultUserRepository userRepository;//todo dev stage
    private final DefaultUserFacade defaultUserFacade;

    @GetMapping
    public List<DefaultUser> getUsers() {//todo dev stage
        return userRepository.findAll();
    }

    @GetMapping(value = "/img/{userId}",produces = { IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
    public byte[] getImage(@PathVariable String userId){
        return defaultUserFacade.getUserImage(userId);
    }

    @PutMapping("/img/{userId}")
    public void updatePhoto(
            @PathVariable String userId,
            @RequestParam MultipartFile photo
    ){
        defaultUserFacade.editPhoto(userId, photo);
    }

    @PatchMapping
    public void edit(@RequestBody DefaultUserEditCommand command) {
        defaultUserFacade.edit(command);
    }
}
