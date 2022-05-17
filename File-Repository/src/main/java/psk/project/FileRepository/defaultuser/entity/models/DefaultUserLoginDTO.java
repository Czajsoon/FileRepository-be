package psk.project.FileRepository.defaultuser.entity.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultUserLoginDTO {

    String login;
    String password;
}
