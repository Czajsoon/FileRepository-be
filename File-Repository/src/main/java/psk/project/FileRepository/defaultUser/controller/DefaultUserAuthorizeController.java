package psk.project.FileRepository.defaultUser.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.defaultUser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.defaultUser.entity.models.DefautUserLoginResponse;
import psk.project.FileRepository.defaultUser.entity.models.DefaultUserLoginDTO;
import psk.project.FileRepository.defaultUser.services.DefaultUserAuthorizeService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authorize")
public class DefaultUserAuthorizeController {
    private final DefaultUserAuthorizeService defaultUserAuthorizeService;

    @PostMapping("/register")
    public void register(@RequestBody DefaultUserDTO defaultUserDTO){
        defaultUserAuthorizeService.register(defaultUserDTO);
    }

    @PostMapping("/login")
    public DefautUserLoginResponse login(@RequestBody DefaultUserLoginDTO loginDTO) {
        return defaultUserAuthorizeService.login(loginDTO);
    }
}
