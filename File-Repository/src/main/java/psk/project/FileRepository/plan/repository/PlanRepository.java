package psk.project.FileRepository.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.plan.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
