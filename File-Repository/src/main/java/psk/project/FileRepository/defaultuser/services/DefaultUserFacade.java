package psk.project.FileRepository.defaultuser.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserEditCommand;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserLoginDTO;
import psk.project.FileRepository.defaultuser.entity.models.DefautUserLoginResponse;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserImageNotFoundException;

@Service
@AllArgsConstructor
public class DefaultUserFacade {
    public final DefaultUserAuthorizeService authorizeService;
    public final DefaultUserEditService editService;
    public final DefaultUserResponseService responseService;

    public void register(DefaultUserDTO defaultUserDTO){
        authorizeService.register(defaultUserDTO);
    }

    public byte[] getUserImage(String userId){
        return responseService.getImage(userId)
            .orElseThrow(DefaultUserImageNotFoundException::new);
    }

    public DefautUserLoginResponse login(DefaultUserLoginDTO loginDTO){
        return authorizeService.login(loginDTO);
    }

    public void editPhoto(String userId, MultipartFile file){
        editService.changePhoto(userId, file);
    }

    public void edit(DefaultUserEditCommand command){
        editService.edit(command);
    }
}
