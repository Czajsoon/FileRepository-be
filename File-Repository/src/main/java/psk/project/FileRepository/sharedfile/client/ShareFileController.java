package psk.project.FileRepository.sharedfile.client;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

  @PutMapping("/cancel/{shareLink}/{fileId}")
  public void cancelSharingFor(
          @PathVariable String shareLink,
          @PathVariable String fileId
  ){
    shareFileFacade.cancelSharingForUser(shareLink, fileId);
  }
}
