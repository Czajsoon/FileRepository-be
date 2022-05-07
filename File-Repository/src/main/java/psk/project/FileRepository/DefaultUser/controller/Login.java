package psk.project.FileRepository.DefaultUser.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.DefaultUser.entity.models.DefautUserLoginResponse;
import psk.project.FileRepository.DefaultUser.entity.models.LoginDTO;
import psk.project.FileRepository.DefaultUser.services.UserQueryService;


@RestController
@AllArgsConstructor
@RequestMapping("/api/authorize")
public class Login {

    private final UserQueryService userQueryService;

    @PostMapping("/login")
    private DefautUserLoginResponse login(@RequestBody LoginDTO loginDTO) {
        return userQueryService.findDefaultUserByLoginAndPassword(loginDTO);
    }


}



