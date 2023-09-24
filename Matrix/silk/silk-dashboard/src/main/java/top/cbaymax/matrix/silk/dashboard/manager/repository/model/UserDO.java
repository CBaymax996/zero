package top.cbaymax.matrix.silk.dashboard.manager.repository.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "table_user")
@Data
public class UserDO {// 不能用record，字段会有修改
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;


    @Column
    private String role;




}
