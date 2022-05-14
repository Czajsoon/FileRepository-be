package psk.project.FileRepository.file.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.file.models.FileDTO;
import psk.project.FileRepository.sharedFile.entity.SharedFile;

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
    @NotNull
    private String fileName;

    @Column
    private String description;

    @Column
    private String fileFormat;

    @Column
    private Long size;

    @Column
    @JsonIgnore
    private String comment;

    @Column
    @JsonIgnore
    private String totalPath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defaultUserID")
    @JsonBackReference
    private DefaultUser defaultUser;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SharedFile> sharedFiles;

    public static File of(FileDTO fileDTO, DefaultUser user) {
        File file = new File();
        file.setDefaultUser(user);
        file.setSize(fileDTO.getSize());
        file.setPath(fileDTO.getPath());
        file.setDescription(fileDTO.getDescription());
        file.setComment(fileDTO.getComment());
        file.setFileName(fileDTO.getFileName());
        file.setTotalPath(fileDTO.getTotalPath());
        file.setFileFormat(fileDTO.getFileFormat());
        return file;
    }
}
