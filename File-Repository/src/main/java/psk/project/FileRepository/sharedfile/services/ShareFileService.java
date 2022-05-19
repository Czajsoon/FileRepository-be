package psk.project.FileRepository.sharedfile.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.exceptions.DefaultUserNotFoundException;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileNotFoundException;
import psk.project.FileRepository.file.repository.FileRepository;
import psk.project.FileRepository.sharedfile.exeptions.ShareLinkBadException;
import psk.project.FileRepository.sharedfile.models.ShareFileDTO;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ShareFileService {
  private final DefaultUserRepository userRepository;
  private final FileRepository fileRepository;

  public void shareFile(String shareLink, String fileId){
    ShareFileDTO fileDTO = buildShareFileDTO(shareLink, fileId);
    handleAddAccessForFileToUser(fileDTO.getFile(),fileDTO.getPermissionUser());
    handleAddAccessibleFilesForUser(fileDTO.getFile(),fileDTO.getPermissionUser());
  }

  private void handleAddAccessibleFilesForUser(File file,DefaultUser accessUser){
    accessUser.getAccessibleFiles().add(file);
    userRepository.save(accessUser);
  }

  private void handleAddAccessForFileToUser(File file,DefaultUser accessUser){
    file.getAccessUsers().add(accessUser);
    file.setShared(true);
    fileRepository.save(file);
  }

  private ShareFileDTO buildShareFileDTO(String shareLink, String fileId){
    DefaultUser accessUser = userRepository.findDefaultUserByShareLink(shareLink)
        .orElseThrow(ShareLinkBadException::new);
    File file = fileRepository.findById(UUID.fromString(fileId))
        .orElseThrow(() -> new FileNotFoundException(fileId));
    if(file.getOwner().getShareLink().equals(shareLink))

    return ShareFileDTO.builder()
        .file(file)
        .permissionUser(accessUser)
        .build();
  }
}
