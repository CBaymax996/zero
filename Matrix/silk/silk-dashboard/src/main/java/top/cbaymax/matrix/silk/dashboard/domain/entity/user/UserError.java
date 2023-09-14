package top.cbaymax.matrix.silk.dashboard.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.ErrorCode;

@Getter
@AllArgsConstructor
public enum UserError implements ErrorCode {

    user_not_find("user001", "未查询到该用户");
    private final String code;

    private final String message;

}
