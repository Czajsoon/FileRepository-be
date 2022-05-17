package psk.project.FileRepository.plan.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.plan.entity.Plan;
import psk.project.FileRepository.plan.models.PlanResponse;
import psk.project.FileRepository.plan.repository.PlanRepository;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;

@Service
@AllArgsConstructor
public class PlanResponseService {
    private final PlanRepository planRepository;

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

    public List<PlanResponse> all() {
        return planRepository.findAll().stream().map(PlanResponse::of).toList();
    }
}
