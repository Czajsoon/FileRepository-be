package psk.project.FileRepository.Status.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.Status.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
}
