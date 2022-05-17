package psk.project.FileRepository.defaultuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DefaultUserRepository extends JpaRepository<DefaultUser, UUID> {

    Optional<DefaultUser> findDefaultUserByLogin(String login);

    Optional<DefaultUser> findDefaultUserByLoginAndPassword(String login, String password);
}
