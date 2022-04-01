package psk.project.FileRepository.Payment.entity;


import lombok.Data;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.Plan.entity.Plan;
import psk.project.FileRepository.Status.entity.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentID;

    @Column
    private Date paymentDate;


    //private DefaultUser defaultUser;

    //private Plan plan;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="statusID")
    private Status status;
}
