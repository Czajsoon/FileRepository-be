package psk.project.FileRepository.defaultuser.entity.models;

import lombok.*;

import java.math.BigInteger;

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
    private BigInteger transfer;
}
