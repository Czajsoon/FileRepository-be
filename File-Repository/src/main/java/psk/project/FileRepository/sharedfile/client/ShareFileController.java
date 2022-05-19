package psk.project.FileRepository.sharedfile.client;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/share")
public class ShareFileController {

  @PutMapping("/{shareLink}")
  public void shareFor(String shareUserLink){

  }
}
