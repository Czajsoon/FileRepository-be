package psk.project.FileRepository.defaultUser.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import psk.project.FileRepository.defaultUser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.payment.entity.Payment;
import psk.project.FileRepository.plan.entity.Plan;
import psk.project.FileRepository.sharedFile.entity.SharedFile;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class DefaultUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID defaultUserID;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "planID")
    private Plan plan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "defaultUser")
    @JsonManagedReference
    private List<File> files;

    @OneToMany(fetch= FetchType.LAZY,mappedBy = "defaultUser")
    private List<Payment> payments;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "defaultUser")
    private List<SharedFile> sharedFiles;

    public static DefaultUser of(DefaultUserDTO dto){
        DefaultUser user = new DefaultUser();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }
}