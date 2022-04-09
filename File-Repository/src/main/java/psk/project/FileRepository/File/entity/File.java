package psk.project.FileRepository.File.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.File.models.FileDTO;
import psk.project.FileRepository.SharedFile.entity.SharedFile;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private UUID fileID;

    @Column
    private String path;

    @Column
    private String fileName;

    @Column
    private String description;

    @Column
    private Long size;

    @Column
    @JsonIgnore
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defaultUserID")
    private DefaultUser defaultUser;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SharedFile> sharedFiles;

    public static File of(FileDTO fileDTO,DefaultUser user){
        File file = new File();
        file.setDefaultUser(user);
        file.setSize(fileDTO.getSize());
        file.setPath(fileDTO.getPath());
        file.setDescription(fileDTO.getDescription());
        file.setComment(fileDTO.getComment());
        file.setFileName(fileDTO.getFileName());
        return file;
    }
}
