package psk.project.FileRepository.defaultUser.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.defaultUser.entity.models.DefautUserLoginResponse;
import psk.project.FileRepository.defaultUser.entity.models.LoginDTO;
import psk.project.FileRepository.defaultUser.services.UserQueryService;


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



