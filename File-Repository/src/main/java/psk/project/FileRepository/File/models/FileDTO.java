package psk.project.FileRepository.File.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class FileDTO {
    private MultipartFile file;
    private String path;
    private String additionalPath;
    private String description;
    private Double size;
    private String ownerId;
}
