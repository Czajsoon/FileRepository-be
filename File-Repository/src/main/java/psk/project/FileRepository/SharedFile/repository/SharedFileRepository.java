package psk.project.FileRepository.SharedFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.project.FileRepository.SharedFile.entity.SharedFile;


public interface SharedFileRepository extends JpaRepository<SharedFile,Long> {
}
