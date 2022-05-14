package psk.project.FileRepository.defaultUser.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.defaultUser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.defaultUser.services.UserQueryService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authorize")
public class Register {

    private final UserQueryService userQueryService;

    @PostMapping("/register")
    Boolean register(@RequestBody DefaultUserDTO defaultUserDTO){
        return userQueryService.registerNewUser(defaultUserDTO);
    }

}
