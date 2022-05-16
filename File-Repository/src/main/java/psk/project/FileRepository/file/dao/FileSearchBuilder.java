package psk.project.FileRepository.file.dao;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.repository.FileSpecification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FileSearchBuilder {

    private final List<SearchCriteria> params;

    public FileSearchBuilder() {
        this.params = new ArrayList<>();
    }

    public FileSearchBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<File> build() {
        if (params.isEmpty())
            return null;

        List<FileSpecification> specs = params.stream()
                .map(FileSpecification::new).toList();

        Specification<File> result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }

}
