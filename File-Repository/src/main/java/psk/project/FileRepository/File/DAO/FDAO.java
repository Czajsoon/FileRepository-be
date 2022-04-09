package psk.project.FileRepository.File.DAO;

import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;

import java.util.List;
import java.util.Optional;

public interface FDAO<T,S,U> {
    Optional<T> get(String id);
    List<S> getAll();
    String save(U dto) throws FileNotSavedException, UserNotFoundException;
}
