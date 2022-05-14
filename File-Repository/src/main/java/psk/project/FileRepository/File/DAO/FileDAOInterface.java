package psk.project.FileRepository.File.DAO;

import java.util.List;
import java.util.Optional;

public interface FileDAOInterface<T, S, U> {
    Optional<T> get(String id);

    List<T> getAll(List<String> idList);

    List<T> getAllByUser(String userId);

    List<T> getAllByPath(String path,String userId);

    List<S> getAll();

    S save(U dto);

    void changeFileName(T file, String filename);

    void changePathFile(T file, String path);

    String getTotalPathFile(String id);

    void deleteFile(T file);
}
