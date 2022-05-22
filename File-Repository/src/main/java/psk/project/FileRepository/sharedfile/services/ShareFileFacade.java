package psk.project.FileRepository.sharedfile.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShareFileFacade {
  private final ShareFileService shareFileService;
  private final ShareFileCancelService shareFileCancelService;

  public void shareFileForUser(String userShareLink, String fileId) {
    shareFileService.shareFile(userShareLink, fileId);
  }

  public void cancelSharingForUser(String userShareLink, String fileId){
    shareFileCancelService.cancelSharing(userShareLink, fileId);
  }
}
