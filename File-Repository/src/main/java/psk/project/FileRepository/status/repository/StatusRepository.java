package psk.project.FileRepository.status.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.status.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
}
