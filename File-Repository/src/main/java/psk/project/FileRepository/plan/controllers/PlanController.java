package psk.project.FileRepository.plan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.plan.entity.Plan;
import psk.project.FileRepository.plan.repository.PlanRepository;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;

@RestController
public class PlanController {

    @Autowired
    private PlanRepository planRepository;

    @PostConstruct
    private void insertPlans() {
        Plan normal = Plan.builder()
                .name("Darmowy")
                .capacity(BigInteger.valueOf(209715200L))
                .price(0.0)
                .build();
        Plan premium = Plan.builder()
                .name("Premium")
                .capacity(BigInteger.valueOf(419430400L))
                .price(29.99)
                .build();
        Plan premiumplus = Plan.builder()
                .name("Premium+")
                .capacity(BigInteger.valueOf(681574400L))
                .price(39.99)
                .build();
        createPlan(normal);
        createPlan(premium);
        createPlan(premiumplus);
    }

    private void createPlan(Plan newPlan) {
        planRepository.save(newPlan);
    }

    @GetMapping("/plan")
    public List<Plan> plans() {
        return planRepository.findAll();
    }
}
