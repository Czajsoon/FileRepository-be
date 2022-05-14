package psk.project.FileRepository.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psk.project.FileRepository.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
