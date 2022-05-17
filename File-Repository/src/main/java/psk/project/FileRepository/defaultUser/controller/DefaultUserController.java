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
import psk.project.FileRepository.plan.entity.Plan;
import psk.project.FileRepository.plan.repository.PlanRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class DefaultUserController {
    private final DefaultUserRepository userRepository;
    private final PlanRepository planRepository;

    @GetMapping
    public ResponseEntity<List<DefaultUser>> getUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostConstruct
    public void init(){
//        Optional<Plan> normal = planRepository.findByName("Normal");
        //TODO zakonczyc init i przetestowac!!!
//        userRepository.save(DefaultUser.of(DefaultUserDTO.builder()
//                .name("Jakub")
//                .surname("Czajkowski")
//                .email("kubaczajkowski25@gmail.com")
//                .login("1234")
//                .transfer(0L)
//                .password("123")
//                .build()));
//
//        userRepository.save(DefaultUser.of(DefaultUserDTO.builder()
//                .name("Jakub")
//                .surname("Czajkowski")
//                .email("kubaczajkowski25@gmail.com")
//                .login("12345")
//                .transfer(0L)
//                .password("123")
//                .build()));
    }
}
