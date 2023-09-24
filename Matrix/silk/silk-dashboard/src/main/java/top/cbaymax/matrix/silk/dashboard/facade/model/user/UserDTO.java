package top.cbaymax.matrix.silk.dashboard.facade.model.user;


// controller的entity对应的是前端对象

import top.cbaymax.matrix.silk.dashboard.domain.entity.user.User;

/**
 * @author 褚浩
 */
public record UserDTO(
        String username,
        String password
) {

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.toString(), user.toString());
    }
}
