package psk.project.FileRepository.DefaultUser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;


public interface DefaultUserRepository extends JpaRepository<DefaultUser,Long> {
}
