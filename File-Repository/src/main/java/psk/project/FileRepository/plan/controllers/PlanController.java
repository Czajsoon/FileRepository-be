package psk.project.FileRepository.plan.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psk.project.FileRepository.plan.models.PlanResponse;
import psk.project.FileRepository.plan.services.PlanFacade;

import java.util.List;

@RestController
@RequestMapping("/plan")
@AllArgsConstructor
public class PlanController {

    private final PlanFacade planFacade;

    @GetMapping
    public List<PlanResponse> plans() {
        return planFacade.all();
    }
}
