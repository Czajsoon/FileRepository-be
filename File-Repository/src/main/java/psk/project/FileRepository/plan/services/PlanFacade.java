package psk.project.FileRepository.plan.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import psk.project.FileRepository.plan.models.PlanResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanFacade {
    private final PlanResponseService planResponseService;

    public List<PlanResponse> all(){
        return planResponseService.all();
    }
}
