package psk.project.FileRepository.payment.entity;


import lombok.Data;
import psk.project.FileRepository.defaultuser.entity.DefaultUser;
import psk.project.FileRepository.plan.entity.Plan;
import psk.project.FileRepository.status.entity.Status;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="defaultUserID")
    private DefaultUser defaultUser;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="planID")
    private Plan plan;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="statusID")
    private Status status;
}
