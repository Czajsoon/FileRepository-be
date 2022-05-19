package psk.project.FileRepository.defaultuser.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.defaultuser.entity.models.*;
import psk.project.FileRepository.defaultuser.services.DefaultUserAuthorizeService;
import psk.project.FileRepository.defaultuser.services.DefaultUserFacade;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authorize")
public class DefaultUserAuthorizeController {
    private final DefaultUserFacade defaultUserFacade;
    private final DefaultUserAuthorizeService defaultUserAuthorizeService;

    @PostMapping("/register")
    public void register(@RequestBody DefaultUserDTO defaultUserDTO) {
        defaultUserFacade.register(defaultUserDTO);
    }

    @PostMapping("/login")
    public DefautUserLoginResponse login(@RequestBody DefaultUserLoginDTO loginDTO) {
        return defaultUserFacade.login(loginDTO);
    }

    @PostMapping("/facebookAuth")
    public DefaultFacebookUserResponse facebookLogin(@RequestBody DefaultFacebookUserDTO defaultFacebookUserDTO){
        return defaultUserAuthorizeService.facebookAuth(defaultFacebookUserDTO);
    }
}
