package top.cbaymax.matrix.silk.dashboard.facade.controller.model;


import top.cbaymax.matrix.silk.dashboard.domain.entity.user.User;

/**
 * @author 褚浩
 */
public record UserVO(
        String username,
        String password
) {

    public static UserVO toVO(User user) {
        return new UserVO(user.toString(), user.toString());
    }
}
