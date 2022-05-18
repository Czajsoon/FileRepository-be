package psk.project.FileRepository.defaultuser.entity.models;

import lombok.*;
import psk.project.FileRepository.plan.entity.Plan;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultFacebookUserResponse {
    private String facebookId;
    private String name;
    private String surname;
    private String email;
    private BigInteger transfer;
    private Plan plan;
}
