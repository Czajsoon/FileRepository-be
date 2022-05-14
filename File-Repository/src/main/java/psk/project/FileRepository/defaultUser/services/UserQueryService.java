package psk.project.FileRepository.defaultUser.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.defaultUser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.defaultUser.entity.models.DefautUserLoginResponse;
import psk.project.FileRepository.defaultUser.entity.models.LoginDTO;
import psk.project.FileRepository.defaultUser.exceptions.RegisterException;
import psk.project.FileRepository.defaultUser.exceptions.WrongAuthorizationDataException;
import psk.project.FileRepository.defaultUser.repository.DefaultUserRepository;


@Service
@AllArgsConstructor
public class UserQueryService {

    private final DefaultUserRepository defaultUserRepository;

    public DefautUserLoginResponse findDefaultUserByLoginAndPassword(LoginDTO loginDTO) {
        DefaultUser defaultUser = defaultUserRepository
                .findDefaultUserByLoginAndPassword(loginDTO.getLogin(), loginDTO.getPassword())
                .stream().findFirst()
                .orElseThrow(WrongAuthorizationDataException::new);

        return DefautUserLoginResponse.of(defaultUser);
    }

    public Boolean registerNewUser(DefaultUserDTO defaultUserDTO){

        try {
            defaultUserRepository.save(DefaultUser.of(DefaultUserDTO.builder()
                    .name(defaultUserDTO.getName())
                    .surname(defaultUserDTO.getSurname())
                    .email(defaultUserDTO.getEmail())
                    .login(defaultUserDTO.getLogin())
                    .password(defaultUserDTO.getPassword())
                    .build()));
        }
        catch(IllegalArgumentException ex){
            throw new RegisterException();
        }
        return true;
    }

}
