package psk.project.FileRepository.defaultUser.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.defaultUser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.defaultUser.entity.models.DefautUserLoginResponse;
import psk.project.FileRepository.defaultUser.entity.models.DefaultUserLoginDTO;
import psk.project.FileRepository.defaultUser.exceptions.DefaultUserLoginAlreadyExistsException;
import psk.project.FileRepository.defaultUser.exceptions.DefaultUserWrongAuthorizationDataException;
import psk.project.FileRepository.defaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.plan.entity.Plan;
import psk.project.FileRepository.plan.repository.PlanRepository;


@Service
@AllArgsConstructor
public class DefaultUserAuthorizeService {

    private final DefaultUserRepository defaultUserRepository;
    private final PlanRepository planRepository;

    public DefautUserLoginResponse login(DefaultUserLoginDTO loginDTO) {
        DefaultUser defaultUser = defaultUserRepository
                .findDefaultUserByLoginAndPassword(loginDTO.getLogin(), loginDTO.getPassword())
                .stream().findFirst()
                .orElseThrow(DefaultUserWrongAuthorizationDataException::new);

        return DefautUserLoginResponse.of(defaultUser);
    }

    public void register(DefaultUserDTO defaultUserDTO){
        Plan plan = planRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);
        validateUserWithLogin(defaultUserDTO.getLogin());
        defaultUserRepository.save(DefaultUser.of(DefaultUserDTO.builder()
                .name(defaultUserDTO.getName())
                .surname(defaultUserDTO.getSurname())
                .email(defaultUserDTO.getEmail())
                .login(defaultUserDTO.getLogin())
                .password(defaultUserDTO.getPassword())
                .build(),plan));
    }

    private void validateUserWithLogin(String login){
        if (defaultUserRepository.findDefaultUserByLogin(login).isPresent()) {
            throw new DefaultUserLoginAlreadyExistsException();
        }
    }
}
