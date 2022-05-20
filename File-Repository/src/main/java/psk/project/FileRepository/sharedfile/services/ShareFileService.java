package psk.project.FileRepository.sharedfile.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileNotFoundException;
import psk.project.FileRepository.file.repository.FileRepository;
import psk.project.FileRepository.sharedfile.exeptions.ShareFileIsAlreadySharedException;
import psk.project.FileRepository.sharedfile.exeptions.ShareFileLinkBadException;
import psk.project.FileRepository.sharedfile.exeptions.ShareFileToSelfException;
import psk.project.FileRepository.sharedfile.models.ShareFileDTO;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ShareFileService {
  private final DefaultUserRepository userRepository;
  private final FileRepository fileRepository;

  public void shareFile(String shareLink, String fileId) {
    ShareFileDTO fileDTO = buildShareFileDTO(shareLink, fileId);
    handleAddAccessForFileToUser(fileDTO.getFile(), fileDTO.getPermissionUser());
    handleAddAccessibleFilesForUser(fileDTO.getFile(), fileDTO.getPermissionUser());
  }

  private void handleAddAccessibleFilesForUser(File file, DefaultUser accessUser) {
    accessUser.getAccessibleFiles().add(file);
    userRepository.save(accessUser);
  }

  private void handleAddAccessForFileToUser(File file, DefaultUser accessUser) {
    file.getDefaultUsers().add(accessUser);
    file.setShared(true);
    fileRepository.save(file);
  }

  private ShareFileDTO buildShareFileDTO(String shareLink, String fileId) {
    DefaultUser accessUser = userRepository.findDefaultUserByShareLink(shareLink)
        .orElseThrow(ShareFileLinkBadException::new);
    File file = fileRepository.findById(UUID.fromString(fileId))
        .orElseThrow(() -> new FileNotFoundException(fileId));
    validateShare(file, accessUser, shareLink);
    return ShareFileDTO.builder()
        .file(file)
        .permissionUser(accessUser)
        .build();
  }

  private void validateShare(File file, DefaultUser accessUser, String shareLink) {
    if (file.getDefaultUsers().contains(accessUser))
      throw new ShareFileIsAlreadySharedException();

    if (file.getDefaultUser().getShareLink().equals(shareLink))
      throw new ShareFileToSelfException();
  }
}
