package psk.project.FileRepository.File.DAO;

import psk.project.FileRepository.DefaultUser.exceptions.UserNotFoundException;
import psk.project.FileRepository.File.exceptions.FileNotSavedException;
import psk.project.FileRepository.File.models.FileDTO;

import java.util.Optional;

public interface FDAO<T> {
    Optional<T> get(String id);
    void save(FileDTO dto) throws FileNotSavedException, UserNotFoundException;
}
