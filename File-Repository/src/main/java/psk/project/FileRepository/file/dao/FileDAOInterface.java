package psk.project.FileRepository.file.dao;

import org.springframework.data.domain.Page;
import psk.project.FileRepository.file.entity.models.FileSearchCommand;
import psk.project.FileRepository.models.PageCommand;

import java.util.List;
import java.util.Optional;

public interface FileDAOInterface<T, S, U> {
    Optional<T> get(String id);

    List<T> getAll(List<String> idList);

    Page<T> getAllByUser(String userId, FileSearchCommand searchCommand, PageCommand pageCommand);

    List<S> getAll();

    S save(U dto);

    void changeFileName(T file, String filename);

    void changePathFile(T file, String path);

    String getTotalPathFile(String id);

    void deleteFile(T file);
}
