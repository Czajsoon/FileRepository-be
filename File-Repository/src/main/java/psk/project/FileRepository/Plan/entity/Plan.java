package psk.project.FileRepository.Plan.entity;

import lombok.Data;
import psk.project.FileRepository.DefaultUser.entity.DefaultUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planID;

    @Column
    private String name;

    @Column
    private Integer capacity;

    @Column
    private Double price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
    private List<DefaultUser> users;

}
