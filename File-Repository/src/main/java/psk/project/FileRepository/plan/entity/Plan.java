package psk.project.FileRepository.plan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import psk.project.FileRepository.defaultUser.entity.DefaultUser;
import psk.project.FileRepository.payment.entity.Payment;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planID;

    @Column
    private String name;

    @Column
    private BigInteger capacity;

    @Column
    private Double price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
    @JsonBackReference
    private List<DefaultUser> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
    private List<Payment> payments;

}
