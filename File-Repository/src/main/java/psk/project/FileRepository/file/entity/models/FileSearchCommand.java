package psk.project.FileRepository.file.entity.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileSearchCommand {
    private String fileFormat;
    private String fileName;
    private Long size;
    private String operation;
    private String path;
    private String description;
    private boolean withSharedFiles = true;
    private Boolean shared;
    private Boolean onlyMyFiles;
    private final String nameFileFormat = "fileFormat";
    private final String nameUser = "defaultUser";
    private final String nameFileName = "pureFileName";
    private final String nameSize = "size";
    private final String namePath = "path";
    private final String nameDescription = "description";
    private final String nameShared = "shared";
}
