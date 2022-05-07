package psk.project.FileRepository.DefaultUser.entity.models;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;

import java.util.UUID;

@Getter
@Setter
@Builder
public class DefautUserLoginResponse {

    private UUID id;
    private String name;
    private String surname;
    private String login;
    private String email;

    public static DefautUserLoginResponse of(DefaultUser defaultUser){
        return DefautUserLoginResponse.builder()
                .id(defaultUser.getDefaultUserID())
                .name(defaultUser.getName())
                .surname(defaultUser.getSurname())
                .login(defaultUser.getLogin())
                .email(defaultUser.getEmail())
                .build();
    }

}

