package psk.project.FileRepository.Plan.entity;

import lombok.*;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;
import psk.project.FileRepository.Payment.entity.Payment;

import javax.persistence.*;
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
    private Double capacity;

    @Column
    private Double price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
    private List<DefaultUser> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
    private List<Payment> payments;

}
