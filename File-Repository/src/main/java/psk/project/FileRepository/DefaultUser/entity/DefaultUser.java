package psk.project.FileRepository.DefaultUser.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import psk.project.FileRepository.DefaultUser.models.DefaultUserDTO;
import psk.project.FileRepository.File.entity.File;
import psk.project.FileRepository.Plan.entity.Plan;
import psk.project.FileRepository.SharedFile.entity.SharedFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class DefaultUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false)
    private String defaultUserID;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String Email;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "planID")
    private Plan plan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "defaultUser")
    private List<File> files;

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
