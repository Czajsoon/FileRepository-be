package psk.project.FileRepository.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PageCommand {
    String nameTotalRecords = "totalRecords";
    String nameTotalPages = "totalPage";
    String nameFiles = "files";
    Integer page;
    Integer size;
}
