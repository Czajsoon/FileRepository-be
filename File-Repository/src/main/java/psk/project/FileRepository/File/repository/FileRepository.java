package psk.project.FileRepository.File.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.project.FileRepository.File.entity.File;

public interface FileRepository extends JpaRepository<File,Long> {
}
