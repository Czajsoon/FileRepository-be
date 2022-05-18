package psk.project.FileRepository.defaultuser.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.defaultuser.entity.models.*;
import psk.project.FileRepository.defaultuser.services.DefaultUserAuthorizeService;

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

    @PostMapping("/facebookAuth")
    public DefaultFacebookUserResponse facebookLogin(@RequestBody DefaultFacebookUserDTO defaultFacebookUserDTO){
        return defaultUserAuthorizeService.facebookAuth(defaultFacebookUserDTO);
    }
}