package psk.project.FileRepository.sharedFile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.sharedFile.entity.SharedFile;


@Repository
public interface SharedFileRepository extends JpaRepository<SharedFile,Long> {
}
