package psk.project.FileRepository.Payment.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.project.FileRepository.Payment.entity.Payment;
import psk.project.FileRepository.Payment.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @PostMapping("/payment")
    Payment addPayment(@RequestBody Payment payment){
        return paymentRepository.save(payment);
    }

    @DeleteMapping("/payment")
    ResponseEntity deletePayment(@RequestBody Long paymentID){
        paymentRepository.deleteById(paymentID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/payment")
    List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping("/paymentById")
    Optional<Payment> getPaymentById(@RequestBody Long paymentID){
        return paymentRepository.findById(paymentID);
    }

}
