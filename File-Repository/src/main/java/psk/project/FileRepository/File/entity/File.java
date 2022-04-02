package psk.project.FileRepository.File.entity;

import lombok.Data;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.SharedFile.entity.SharedFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


}
