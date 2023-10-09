package top.cbaymax.matrix.silk.dashboard.facade.controller.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import top.cbaymax.matrix.silk.dashboard.domain.entity.user.User;
import top.cbaymax.matrix.silk.dashboard.facade.validate.ValidateGroup.Insert;
import top.cbaymax.matrix.silk.dashboard.facade.validate.ValidateGroup.Update;

/**
 * @author 褚浩
 */
public record UserVO(

        @NotNull(message = "更新时必须指定id ", groups = {Update.class})
        Long id,

        @NotNull(message = "用户名不能为空")
        @Size(message = "用户名称长度 [2-8] ", min = 2, max = 8, groups = {Insert.class, Update.class})
        String username,

        @Size(message = "密码长度 [8-16] ", min = 8, max = 16, groups = {Insert.class, Update.class})
        String password,

        String nickName



) {

    public static UserVO toVO(@NotNull User user) {
        return null;
    }
}
