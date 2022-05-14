package psk.project.FileRepository.defaultUser.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.defaultUser.entity.models.DefaultUserDTO;
import psk.project.FileRepository.defaultUser.repository.DefaultUserRepository;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class DefaultUserController {
    private final DefaultUserRepository userRepository;

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
                .login("1234")
                .password("123")
                .build()));
    }
}
