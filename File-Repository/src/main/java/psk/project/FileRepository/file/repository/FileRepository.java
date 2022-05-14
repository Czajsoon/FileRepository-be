package psk.project.FileRepository.file.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.file.entity.File;


import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    List<File> findAllByPathAndDefaultUser(String path, DefaultUser user);
//    Page<File> findAllByPathAndDefaultUser(String path, DefaultUser user, Pageable pageable);
//    List<File> findAllByDefaultUser(DefaultUser user);
    Page<File> findAllByDefaultUser(DefaultUser user, Pageable pageable);
}
