package psk.project.FileRepository.Status.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.project.FileRepository.Status.entity.Status;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
