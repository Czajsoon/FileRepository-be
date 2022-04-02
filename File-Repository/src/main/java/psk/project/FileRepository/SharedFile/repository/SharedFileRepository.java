package psk.project.FileRepository.SharedFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.SharedFile.entity.SharedFile;


@Repository
public interface SharedFileRepository extends JpaRepository<SharedFile,Long> {
}
