package psk.project.FileRepository.Plan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.project.FileRepository.Plan.entity.Plan;
import psk.project.FileRepository.Plan.repository.PlanRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class PlanController {

    @Autowired
    private PlanRepository planRepository;

    @PostConstruct
    private void insertPlans(){
//    Plan normal= Plan.builder().name("Normal").capacity(0.2).price(19.99).build();
//    Plan premium= Plan.builder().name("Premium").capacity(0.5).price(29.99).build();
//    Plan premiumplus= Plan.builder().name("Premium+").capacity(1.0).price(39.99).build();
//        planRepository.save(normal);
//        planRepository.save(premium);
//        planRepository.save(premiumplus);

    }

    @PostMapping("/plan")
    Plan newPlan(@RequestBody Plan newPlan){
         return planRepository.save(newPlan);
    }

    @GetMapping("/plan")
    List<Plan> listAllPlans(){
        return planRepository.findAll();
    }

    @DeleteMapping("/plan")
    ResponseEntity deletePlan(@RequestBody Long planID){
        planRepository.deleteById(planID);
        return ResponseEntity.ok().build();
    }


  
}
