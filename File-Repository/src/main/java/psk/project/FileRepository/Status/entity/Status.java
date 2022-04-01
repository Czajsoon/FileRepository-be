package psk.project.FileRepository.Status.entity;

import lombok.Data;
import psk.project.FileRepository.Payment.entity.Payment;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Status {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long statusID;

    @Column
    private String name;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "status")
    private List<Payment> payments;
}
