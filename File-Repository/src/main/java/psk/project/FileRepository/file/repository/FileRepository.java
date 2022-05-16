package psk.project.FileRepository.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.file.entity.File;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID>, JpaSpecificationExecutor<File> {
    List<File> findAllByPathAndDefaultUser(String path, DefaultUser user);
}
