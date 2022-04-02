package psk.project.FileRepository.SharedFile.entity;

import lombok.Data;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.File.entity.File;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fileID")
    private File file;
}
