package psk.project.FileRepository.DefaultUser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.DefaultUser.models.DefaultUserDTO;
import psk.project.FileRepository.DefaultUser.repository.DefaultUserRepository;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/users")
public class DefaultUserController {
    private final DefaultUserRepository userRepository;

    public DefaultUserController(DefaultUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostConstruct
    public void init(){
        //TODO zakonczyc init i przetestowac!!!
        userRepository.save(DefaultUser.of(DefaultUserDTO.builder()
                .name("Jakub")
                .surname("Czajkowski")
                .email("kubaczajkowski25@gmail.com")
                .login("123")
                .password("123")
                .build()));
    }
}
