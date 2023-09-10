package top.cbaymax.matrix.silk.dashboard.controller;

import org.springframework.data.repository.CrudRepository;
import top.cbaymax.matrix.silk.dashboard.controller.jpa.UserDO;

public interface UserRepository extends CrudRepository<UserDO, Integer> {

}