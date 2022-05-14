package psk.project.FileRepository.file.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@Builder
public class FileDTO {
    private MultipartFile file;
    private String path;
    private String additionalPath;
    private String description;
    private Long size;
    private UUID ownerId;
    private Boolean fileExists;
    private UUID additionalFileName;
    private String comment;
    private String fileName;
    private String fileFormat;
    private String totalPath;

    public String getOwnerId() {
        return ownerId.toString();
    }
}
