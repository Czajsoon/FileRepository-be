package psk.project.FileRepository.defaultuser.entity.models;

import lombok.*;
import psk.project.FileRepository.plan.entity.Plan;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultFacebookUserResponse {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private BigInteger transfer;
    private Plan plan;
}
