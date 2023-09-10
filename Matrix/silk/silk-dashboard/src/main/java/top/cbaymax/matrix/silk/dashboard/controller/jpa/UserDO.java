package top.cbaymax.matrix.silk.dashboard.controller.jpa;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "table_user")
@Data
public class UserDO {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;


}
