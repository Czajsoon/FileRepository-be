package psk.project.FileRepository.DefaultUser.entity.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    String login;
    String password;
}
