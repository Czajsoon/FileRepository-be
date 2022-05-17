package psk.project.FileRepository.sharedfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.sharedfile.entity.SharedFile;


@Repository
public interface SharedFileRepository extends JpaRepository<SharedFile,Long> {
}
