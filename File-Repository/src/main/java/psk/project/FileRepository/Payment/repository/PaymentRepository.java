package psk.project.FileRepository.Payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import psk.project.FileRepository.Payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
