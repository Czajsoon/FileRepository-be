package psk.project.FileRepository.Plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.project.FileRepository.Plan.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
