package psk.project.FileRepository.file.entity.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileCommand {
    String path;
    String fileName;
    String description;
    String fileId;
}
