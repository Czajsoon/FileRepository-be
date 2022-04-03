package psk.project.FileRepository.File.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.SharedFile.entity.SharedFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class File {

    @Id
    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    @Column(name = "ID", updatable = false, nullable = false)
    private Long fileID;

    @Column
    private String path;

    @Column
    private String description;

    @Column
    private Double size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defaultUserID")
    private DefaultUser defaultUser;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SharedFile> sharedFiles;


}
