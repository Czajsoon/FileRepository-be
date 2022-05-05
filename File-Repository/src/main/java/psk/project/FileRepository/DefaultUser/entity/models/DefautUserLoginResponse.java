package psk.project.FileRepository.DefaultUser.entity.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
}
