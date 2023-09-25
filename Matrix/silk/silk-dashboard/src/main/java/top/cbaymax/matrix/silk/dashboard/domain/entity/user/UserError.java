package top.cbaymax.matrix.silk.dashboard.domain.entity.user;

import top.cbaymax.matrix.silk.dashboard.infrastructure.error.ErrorCode;


public enum UserError implements ErrorCode {

    user_not_find("user001", "未查询到该用户");

    public final String code;

    public final String message;


    UserError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
