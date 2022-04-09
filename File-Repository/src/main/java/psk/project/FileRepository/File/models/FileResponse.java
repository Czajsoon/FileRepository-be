package psk.project.FileRepository.File.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import psk.project.FileRepository.File.entity.File;

@Builder
@Getter
@Setter
public class FileResponse {
    private String ownerId;
    private String fileId;
    private Long size;
    private String path;
    private String description;
//    private List<String>

    public static FileResponse of(File file) {
        String description = file.getDescription();
        if (description == null)
            description = "Brak";

        String path = "Folder główny";
        if (!file.getPath().equals("Folder główny"))
            path = file.getPath();

        return FileResponse.builder()
                .description(description)
                .size(file.getSize())
                .ownerId(file.getDefaultUser()
                        .getDefaultUserID()
                        .toString())
                .path(path)
                .fileId(file.getFileID().toString())
                .build();
    }
}
