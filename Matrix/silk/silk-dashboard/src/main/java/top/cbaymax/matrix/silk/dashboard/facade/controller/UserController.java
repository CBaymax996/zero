package top.cbaymax.matrix.silk.dashboard.facade.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.cbaymax.matrix.silk.dashboard.facade.controller.model.UserVO;
import top.cbaymax.matrix.silk.dashboard.facade.model.base.PageResult;
import top.cbaymax.matrix.silk.dashboard.facade.model.user.UserPageQuery;
import top.cbaymax.matrix.silk.dashboard.facade.validate.ValidateGroup;
import top.cbaymax.matrix.silk.dashboard.gateway.repository.UserRepository;
import top.cbaymax.matrix.silk.dashboard.gateway.repository.model.UserDO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author 褚浩
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserVO add(
            @Validated(ValidateGroup.Insert.class)
            @RequestBody UserVO user) {
        System.out.println(user);
        return null;
    }


    @RequestMapping(value = "/query", method = {RequestMethod.GET, RequestMethod.POST})
    public UserVO query(@NotNull(message = "查询id不能为空") @RequestParam Long userId) {
        System.out.println(userId);
        return null;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public UserVO delete(@NotNull(message = "删除id不能为空") @RequestParam Long userId) {
        System.out.println(userId);
        return null;
    }

    @RequestMapping("/page-query")
    public PageResult<UserVO> queryByPage(UserPageQuery pageQuery) {
        List<UserDO> all = userRepository.findAll();
        System.out.println(all);

        Optional<UserDO> admin = userRepository.findByName("admin");
        System.out.println(admin);
        return new PageResult<>(Collections.emptyList());
    }
}
