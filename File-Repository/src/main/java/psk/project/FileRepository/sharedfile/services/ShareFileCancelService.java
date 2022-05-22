package psk.project.FileRepository.sharedfile.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.exceptions.FileNotFoundException;
import psk.project.FileRepository.file.repository.FileRepository;
import psk.project.FileRepository.sharedfile.exeptions.ShareFileLinkBadException;
import psk.project.FileRepository.sharedfile.models.ShareFileDTO;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ShareFileCancelService {

    private final DefaultUserRepository userRepository;
    private final FileRepository fileRepository;

    public void cancelSharing(String shareLink, String fileId) {
        ShareFileDTO shareFileDTO = buildShareFileDTO(shareLink, fileId);
        handleFileSide(shareFileDTO);
        handleUserSide(shareFileDTO);
    }

    private void handleFileSide(ShareFileDTO shareFileDTO) {
        shareFileDTO.getFile().getDefaultUsers().remove(shareFileDTO.getPermissionUser());
        if (shareFileDTO.getFile().getDefaultUsers().isEmpty())
            shareFileDTO.getFile().setShared(false);

        fileRepository.save(shareFileDTO.getFile());
    }

    private void handleUserSide(ShareFileDTO shareFileDTO) {
        shareFileDTO.getPermissionUser().getAccessibleFiles().remove(shareFileDTO.getFile());
        userRepository.save(shareFileDTO.getPermissionUser());
    }

    private ShareFileDTO buildShareFileDTO(String shareLink, String fileId) {
        DefaultUser accessUser = userRepository.findDefaultUserByShareLink(shareLink)
                .orElseThrow(ShareFileLinkBadException::new);
        File file = fileRepository.findById(UUID.fromString(fileId))
                .orElseThrow(() -> new FileNotFoundException(fileId));
        return ShareFileDTO.builder()
                .file(file)
                .permissionUser(accessUser)
                .build();
    }
}
