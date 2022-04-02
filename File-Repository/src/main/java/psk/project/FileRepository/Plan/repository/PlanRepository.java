package psk.project.FileRepository.Plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.Plan.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
