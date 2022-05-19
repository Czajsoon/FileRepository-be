package psk.project.FileRepository.sharedfile.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShareFileFacade {
  private final ShareFileService shareFileService;

}
