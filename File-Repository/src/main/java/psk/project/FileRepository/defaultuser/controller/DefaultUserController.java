package psk.project.FileRepository.defaultuser.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.defaultuser.repository.DefaultUserRepository;

import java.util.List;

@Controller
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class DefaultUserController {
    private final DefaultUserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<DefaultUser>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}
