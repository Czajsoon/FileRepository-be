package psk.project.FileRepository.File.DAO;

import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface FDAO<T,S,U> {
    Optional<T> get(String id);
    List<T> getAll(List<String> idList);
    List<S> getAll();
    S save(U dto) throws FileNotSavedException, UserNotFoundException;
    String getTotalPathFile(String id) throws NoSuchElementException;
}
