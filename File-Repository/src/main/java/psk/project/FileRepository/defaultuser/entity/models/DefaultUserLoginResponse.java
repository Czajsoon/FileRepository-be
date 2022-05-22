package psk.project.FileRepository.defaultuser.entity.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@Builder
public class DefaultUserLoginResponse {

    private UUID id;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String shareLink;
    private BigInteger transferUsage;
    private PlanDTO planDTO;

    @Builder
    @Getter
    @Setter
    static class PlanDTO{
        private String name;
    }

    public static DefaultUserLoginResponse of(DefaultUser defaultUser){
        return DefaultUserLoginResponse.builder()
                .id(defaultUser.getDefaultUserID())
                .name(defaultUser.getName())
                .surname(defaultUser.getSurname())
                .login(defaultUser.getLogin())
                .email(defaultUser.getEmail())
                .planDTO(PlanDTO.builder()
                        .name(defaultUser
                                .getPlan()
                                .getName())
                        .build())
                .shareLink(defaultUser.getShareLink())
                .transferUsage(defaultUser.getTransferUsage())
                .build();
    }

}

