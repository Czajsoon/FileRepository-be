package psk.project.FileRepository.DefaultUser.entity.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DefaultUserDTO {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
}
