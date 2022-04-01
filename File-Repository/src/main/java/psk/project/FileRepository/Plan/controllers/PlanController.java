package psk.project.FileRepository.Plan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.Plan.entity.Plan;
import psk.project.FileRepository.Plan.repository.PlanRepository;

@RestController
public class PlanController {

    @Autowired
    private PlanRepository planRepository;

    @PostMapping("/plan")
    Plan newPlan(@RequestBody Plan newPlan){
         return planRepository.save(newPlan);
    }

}
