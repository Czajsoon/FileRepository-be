package psk.project.FileRepository.defaultuser.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserEditCommand;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.defaultuser.services.DefaultUserFacade;

import java.util.List;

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

    @PatchMapping
    public void edit(
            @RequestParam(required = false) MultipartFile file,
            @RequestParam String id,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String email
    ) {
        defaultUserFacade.edit(DefaultUserEditCommand.builder()
                .id(id)
                .file(file)
                .login(login)
                .password(password)
                .name(name)
                .surname(surname)
                .email(email)
                .build());
    }

}
