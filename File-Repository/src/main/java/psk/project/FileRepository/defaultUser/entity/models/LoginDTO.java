package psk.project.FileRepository.defaultUser.entity.models;

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
