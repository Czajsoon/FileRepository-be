package psk.project.FileRepository.defaultuser.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserNotFoundException;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultUserResponseService {

  private final DefaultUserRepository defaultUserRepository;

  public Optional<byte[]> getImage(String userId) {
    DefaultUser user = defaultUserRepository.findById(UUID.fromString(userId))
        .orElseThrow(() -> new DefaultUserNotFoundException(userId));
    try {
      InputStream stream = Files.newInputStream(Path.of(user.getPhotoLink()));
      return Optional.of(stream.readAllBytes());
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
