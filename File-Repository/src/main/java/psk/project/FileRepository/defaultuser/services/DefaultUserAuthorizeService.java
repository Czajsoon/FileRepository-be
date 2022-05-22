package psk.project.FileRepository.defaultuser.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.entity.models.*;
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





    private boolean validateUserWithFacebookId(String facebookId){
        if(defaultUserRepository.findDefaultUserByFacebookId(facebookId).isPresent()) {
            return true;
        }
        else{
            return false;
        }
    }
    private void facebookRegister(DefaultFacebookUserDTO defaultFacebookUserDTO){
        Plan plan = planRepository.findById(NORMAL.getValue())
                .orElseThrow(IllegalArgumentException::new);

        defaultUserRepository.save(DefaultUser.of(DefaultFacebookUserDTO.builder()
                .facebookId(defaultFacebookUserDTO.getFacebookId())
                .name(defaultFacebookUserDTO.getName())
                .surname(defaultFacebookUserDTO.getSurname())
                .email(defaultFacebookUserDTO.getEmail())
                .build(), plan));
    }

    private DefaultFacebookUserResponse facebookLogin(DefaultFacebookUserDTO defaultFacebookUserDTO){

        DefaultUser defaultUser= defaultUserRepository
                .findDefaultUserByFacebookId(defaultFacebookUserDTO.getFacebookId())
                .stream()
                .findFirst()
                .orElseThrow(DefaultUserWrongAuthorizationDataException::new);

        return DefaultFacebookUserResponse.builder()
                .id(defaultUser.getDefaultUserID())
                .name(defaultUser.getName())
                .surname(defaultUser.getSurname())
                .email(defaultUser.getEmail())
                .transfer(defaultUser.getTransferUsage())
                .plan(defaultUser.getPlan())
                .build();

    }

    public DefaultFacebookUserResponse facebookAuth(DefaultFacebookUserDTO defaultFacebookUserDTO){
        if(validateUserWithFacebookId(defaultFacebookUserDTO.getFacebookId())){
            return facebookLogin(defaultFacebookUserDTO);
        }
        else{
            facebookRegister(defaultFacebookUserDTO);
            return facebookLogin(defaultFacebookUserDTO);
        }
    }






    }

