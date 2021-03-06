package psk.project.FileRepository.file.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.file.entity.models.FileDTO;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class File {

  @Id
  @GeneratedValue(generator = "UUID")
  @Column(name = "ID", updatable = false, nullable = false, unique = true)
  private UUID fileId;
  @Column private String path;
  @Column @NotNull private String fileName;
  @Column private String description;
  @Column private String fileFormat;
  @Column private String pureFileName;
  @Column private BigInteger size;
  @Column private Boolean shared;
  @Column @JsonIgnore private String comment;
  @Column @JsonIgnore private String totalPath;
  @ManyToOne(fetch = FetchType.EAGER) @JsonBackReference
  private DefaultUser defaultUser;
  @JsonBackReference
  @ManyToMany
  @JoinTable(
      name = "access_users",
      joinColumns = @JoinColumn(name = "file_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<DefaultUser> defaultUsers = new ArrayList<>();

  public static File of(FileDTO fileDTO, DefaultUser user) {
    File file = new File();
    file.setDefaultUser(user);
    file.setSize(fileDTO.getSize());
    file.setPath(fileDTO.getPath());
    file.setPureFileName(fileDTO.getPureFileName());
    file.setDescription(fileDTO.getDescription());
    file.setComment(fileDTO.getComment());
    file.setShared(false);
    file.setFileName(fileDTO.getFileName());
    file.setTotalPath(fileDTO.getTotalPath());
    file.setFileFormat(fileDTO.getFileFormat());
    return file;
  }
}
