package psk.project.FileRepository.plan.models;

import lombok.Builder;
import lombok.Value;
import psk.project.FileRepository.plan.entity.Plan;

import java.math.BigInteger;

@Value
@Builder
public class PlanResponse {
    Long id;
    String name;
    BigInteger capacity;
    Double price;

    public static PlanResponse of(Plan plan) {
        return PlanResponse.builder()
                .id(plan.getPlanID())
                .name(plan.getName())
                .capacity(plan.getCapacity())
                .price(plan.getPrice())
                .build();
    }
}
