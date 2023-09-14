package top.cbaymax.matrix.silk.dashboard.manager.repository;

import org.springframework.data.repository.CrudRepository;
import top.cbaymax.matrix.silk.dashboard.manager.repository.jpa.UserDO;

public interface UserRepository extends CrudRepository<UserDO, Integer> {

}