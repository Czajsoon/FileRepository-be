package psk.project.FileRepository.file.entity.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FileDTO {
    private MultipartFile file;
    private String path;
    private String additionalPath;
    private String description;
    private BigInteger size;
    private UUID ownerId;
    private Boolean fileExists;
    private UUID additionalFileName;
    private String comment;
    private String fileName;
    private String fileFormat;
    private String totalPath;
    private String pureFileName;

    public String getOwnerId() {
        return ownerId.toString();
    }
}
