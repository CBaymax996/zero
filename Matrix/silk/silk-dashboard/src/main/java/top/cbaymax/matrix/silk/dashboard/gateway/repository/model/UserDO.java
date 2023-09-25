package top.cbaymax.matrix.silk.dashboard.gateway.repository.model;


import jakarta.persistence.*;

@Entity
@Table(name = "table_user")
public class UserDO {// 不能用record，字段会有修改
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column
    public String name;


    @Column
    public String role;


}
