package psk.project.FileRepository.DefaultUser.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.DefaultUser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.DefaultUser.services.UserQueryService;

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
