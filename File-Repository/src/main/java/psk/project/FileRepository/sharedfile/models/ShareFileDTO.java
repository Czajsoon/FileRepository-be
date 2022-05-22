package psk.project.FileRepository.sharedfile.models;

import lombok.Builder;
import lombok.Value;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.file.entity.File;

@Value
@Builder
public class ShareFileDTO {
  File file;
  DefaultUser permissionUser;
}
