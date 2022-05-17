package psk.project.FileRepository.file.dao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileSearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
