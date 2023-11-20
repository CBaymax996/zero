package top.cbaymax.matrix.silk.dashboard.gateway.repository;

import org.springframework.data.repository.CrudRepository;
import top.cbaymax.matrix.silk.dashboard.gateway.repository.model.UserDO;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDO, Integer> {

    List<UserDO> findAll();

    Optional<UserDO> findByName(String name);
}