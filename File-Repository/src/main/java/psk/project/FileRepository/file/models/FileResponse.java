package psk.project.FileRepository.file.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import psk.project.FileRepository.file.entity.File;

@Builder
@Getter
@Setter
public class FileResponse {
    private String ownerId;
    private String fileId;
    private Long size;
    private String path;
    private String fileName;
    private String fileFormat;
    private String description;
    private String comment;
    private String fileTotalPath;
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
                .fileFormat(file.getFileFormat())
                .ownerId(file.getDefaultUser()
                        .getDefaultUserID()
                        .toString())
                .fileTotalPath(System.getProperty("user.dir") + "/server/" + file.getTotalPath())
                .path(path)
                .fileName(file.getPureFileName())
                .comment(file.getComment())
                .fileId(file.getFileID().toString())
                .build();
    }
}
