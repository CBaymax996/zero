package top.cbaymax.matrix.silk.dashboard.gateway.repository;

import org.springframework.data.repository.CrudRepository;
import top.cbaymax.matrix.silk.dashboard.gateway.repository.model.UserDO;

public interface UserRepository extends CrudRepository<UserDO, Integer> {

}