package psk.project.FileRepository.sharedfile.client;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.sharedfile.services.ShareFileFacade;

@RestController
@RequestMapping("/api/share")
@AllArgsConstructor
public class ShareFileController {
  private final ShareFileFacade shareFileFacade;

  @PutMapping("/{shareLink}/{fileId}")
  public void shareFor(
      @PathVariable String shareLink,
      @PathVariable String fileId
  ) {
    shareFileFacade.shareFileForUser(shareLink, fileId);
  }
}
