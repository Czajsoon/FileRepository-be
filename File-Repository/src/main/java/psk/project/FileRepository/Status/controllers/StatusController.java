package psk.project.FileRepository.Status.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psk.project.FileRepository.Status.entity.Status;
import psk.project.FileRepository.Status.repository.StatusRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
public class StatusController {

    @Autowired
    StatusRepository statusRepository;

    @PostConstruct
    private void insertStatuses(){
        Status rejected= Status.builder().name("Rejected").build();
        Status waiting= Status.builder().name("Waiting").build();
        Status accepted= Status.builder().name("Accepted").build();
        statusRepository.save(rejected);
        statusRepository.save(waiting);
        statusRepository.save(accepted);
    }

    @PostMapping("/status")
    Status newStatus(@RequestBody Status status){
        return statusRepository.save(status);
    }

    @GetMapping("/status")
    List<Status> getAllStatuses(){
        return statusRepository.findAll();
    }

    @GetMapping("/statusById")
    Optional<Status> getStatusById(@RequestBody Long statusID){
        return statusRepository.findById(statusID);
    }


    @DeleteMapping("/status")
    ResponseEntity deleteStatus(@RequestBody Long statusID){
        statusRepository.deleteById(statusID);
        return ResponseEntity.ok().build();
    }
    }



