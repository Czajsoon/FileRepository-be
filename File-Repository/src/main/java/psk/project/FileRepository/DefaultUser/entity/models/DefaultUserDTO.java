package psk.project.FileRepository.DefaultUser.entity.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultUserDTO {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
}
