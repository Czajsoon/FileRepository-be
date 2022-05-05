package psk.project.FileRepository.DefaultUser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;

import java.util.UUID;

@Repository
public interface DefaultUserRepository extends JpaRepository<DefaultUser, UUID> {
    DefaultUser findDefaultUserByLogin(String login);
}
