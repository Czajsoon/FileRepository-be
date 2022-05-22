package psk.project.FileRepository.defaultuser.entity.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DefaultUserEditCommand {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private String newPassword;
}
