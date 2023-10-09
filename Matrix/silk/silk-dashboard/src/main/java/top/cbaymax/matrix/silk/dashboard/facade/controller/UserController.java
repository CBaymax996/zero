package top.cbaymax.matrix.silk.dashboard.facade.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.cbaymax.matrix.silk.dashboard.facade.controller.model.UserVO;
import top.cbaymax.matrix.silk.dashboard.facade.model.base.PageResult;
import top.cbaymax.matrix.silk.dashboard.facade.model.user.UserPageQuery;
import top.cbaymax.matrix.silk.dashboard.facade.validate.ValidateGroup;

import java.util.Collections;

/**
 * @author 褚浩
 */
@RestController
@RequestMapping("/user")
public class UserController {

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
        return new PageResult<>(Collections.emptyList());
    }
}
