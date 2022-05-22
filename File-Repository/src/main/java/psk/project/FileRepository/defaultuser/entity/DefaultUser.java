package psk.project.FileRepository.defaultuser.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import psk.project.FileRepository.defaultuser.entity.models.DefaultFacebookUserDTO;
import psk.project.FileRepository.defaultuser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.payment.entity.Payment;
import psk.project.FileRepository.plan.entity.Plan;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class DefaultUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID defaultUserID;
    @Column private String name;
    @Column private String surname;
    @Column private String login;
    @Column private String photoLink;
    @Column private String shareLink;
    @Column private String password;
    @Column private String email;
    @Column private BigInteger transferUsage;
    @Column private String facebookId;
    private static final String SHARE_PREFIX = "share.";

    @ManyToOne(fetch= EAGER) @JoinColumn(name = "planID") @JsonManagedReference
    private Plan plan;
    @OneToMany(fetch = LAZY, mappedBy = "defaultUser")
    @JsonManagedReference
    private List<File> files = new ArrayList<>();
    @OneToMany(fetch= LAZY)
    private List<Payment> payments = new ArrayList<>();
    @ManyToMany(mappedBy = "defaultUsers") @JsonManagedReference
    private List<File> accessibleFiles = new ArrayList<>();

    public static DefaultUser of(DefaultUserDTO dto){
        DefaultUser user = new DefaultUser();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setShareLink(SHARE_PREFIX + UUID.randomUUID());
        user.setTransferUsage(dto.getTransfer());
        return user;
    }

    public static DefaultUser of(DefaultUserDTO dto,Plan plan){
        DefaultUser user = new DefaultUser();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhotoLink(dto.getPhotoLink());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setShareLink(SHARE_PREFIX + UUID.randomUUID());
        user.setPlan(plan);
        user.setTransferUsage(BigInteger.ZERO);
        return user;
    }

    public static DefaultUser of(DefaultFacebookUserDTO dto, Plan plan){
        DefaultUser user = new DefaultUser();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setTransferUsage(BigInteger.ZERO);
        user.setFacebookId(dto.getFacebookId());
        user.setShareLink(SHARE_PREFIX + UUID.randomUUID());
        user.setPlan(plan);
        return user;
    }
}
