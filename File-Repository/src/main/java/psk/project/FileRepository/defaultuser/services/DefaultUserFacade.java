package psk.project.FileRepository.defaultuser.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserEditCommand;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserLoginDTO;
import psk.project.FileRepository.defaultuser.entity.models.DefautUserLoginResponse;

@Service
@AllArgsConstructor
public class DefaultUserFacade {
    public final DefaultUserAuthorizeService defaultUserAuthorizeService;
    public final DefaultUserEditService defaultUserEditService;

    public void register(DefaultUserDTO defaultUserDTO){
        defaultUserAuthorizeService.register(defaultUserDTO);
    }

    public DefautUserLoginResponse login(DefaultUserLoginDTO loginDTO){
        return defaultUserAuthorizeService.login(loginDTO);
    }

    public void edit(DefaultUserEditCommand command){
        defaultUserEditService.edit(command);
    }
}
