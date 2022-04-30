package psk.project.FileRepository.File.DAO;

import java.util.List;
import java.util.Optional;

public interface FileDAOInterface<T,S,U> {
    Optional<T> get(String id);
    List<T> getAll(List<String> idList);
    List<S> getAll();
    S save(U dto);
    String getTotalPathFile(String id);
}
