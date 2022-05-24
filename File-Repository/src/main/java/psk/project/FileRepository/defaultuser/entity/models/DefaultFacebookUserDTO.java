package psk.project.FileRepository.defaultuser.entity.models;

import lombok.*;

import java.math.BigInteger;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultFacebookUserDTO {
    private String facebookId;
    private String name;
    private String surname;
    private String email;
    private String photoLink;
    private BigInteger transfer;
}
