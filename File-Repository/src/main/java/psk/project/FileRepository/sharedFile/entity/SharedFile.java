package psk.project.FileRepository.sharedFile.entity;

import lombok.Data;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.file.entity.File;

import javax.persistence.*;

@Entity
@Data
public class SharedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sharedFileID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defaultUserID")
    private DefaultUser defaultUser;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="fileID")
    private File file;
}
