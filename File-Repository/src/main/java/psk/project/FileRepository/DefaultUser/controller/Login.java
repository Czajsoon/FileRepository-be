package psk.project.FileRepository.DefaultUser.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.DefaultUser.entity.models.LoginDTO;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;
import psk.project.FileRepository.DefaultUser.entity.models.DefautUserLoginResponse;

@RestController
public class Login {

    @Autowired
    private DefaultUserRepository defaultUserRepository;

    @PostMapping("/login")
    private ResponseEntity<?> loginCheck(@RequestBody LoginDTO loginDTO){

        if(defaultUserRepository.findDefaultUserByLogin(loginDTO.getLogin()).getPassword().equals(loginDTO.getPassword())){

            DefaultUser defaultUser= defaultUserRepository.findDefaultUserByLogin(loginDTO.getLogin());



            DefautUserLoginResponse response= DefautUserLoginResponse.builder()
                    .id(defaultUser.getDefaultUserID())
                    .name(defaultUser.getName())
                    .surname(defaultUser.getSurname())
                    .login(defaultUser.getLogin())
                    .email(defaultUser.getEmail())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }
        else{

            return new ResponseEntity<>("razdwatrzy",HttpStatus.FORBIDDEN);
        }



    }


}
