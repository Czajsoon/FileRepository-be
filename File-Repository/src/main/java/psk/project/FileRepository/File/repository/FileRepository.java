package psk.project.FileRepository.File.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.File.entity.File;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {

}
