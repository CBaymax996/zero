package top.cbaymax.matrix.silk.dashboard.infrastructure.error;

public enum CommonError implements ErrorCode {

    unknown_internal_exception("SYS_0001", "系统内部异常"),

    invalid_param("COMMON_0001", "参数不合法"),

    timeout("SYS_0002", "超时"),
    ;


    CommonError(String code, String message) {
        init(code, message);
    }

}
