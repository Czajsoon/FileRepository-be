package psk.project.FileRepository.defaultuser.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserLoginDTO;
import psk.project.FileRepository.defaultuser.entity.models.DefautUserLoginResponse;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserLoginAlreadyExistsException;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserWrongAuthorizationDataException;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.plan.entity.Plan;
import psk.project.FileRepository.plan.repository.PlanRepository;

import static psk.project.FileRepository.plan.models.PlanIds.NORMAL;


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

    public void register(DefaultUserDTO defaultUserDTO) {
        Plan plan = planRepository.findById(NORMAL.getValue())
                .orElseThrow(IllegalArgumentException::new);
        validateUserWithLogin(defaultUserDTO.getLogin());
        defaultUserRepository.save(DefaultUser.of(DefaultUserDTO.builder()
                .name(defaultUserDTO.getName())
                .surname(defaultUserDTO.getSurname())
                .email(defaultUserDTO.getEmail())
                .login(defaultUserDTO.getLogin())
                .password(defaultUserDTO.getPassword())
                .build(), plan));
    }

    private void validateUserWithLogin(String login) {
        if (defaultUserRepository.findDefaultUserByLogin(login).isPresent()) {
            throw new DefaultUserLoginAlreadyExistsException();
        }
    }
}
