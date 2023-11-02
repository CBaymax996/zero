package top.cbaymax.matrix.silk.dashboard.infrastructure.error;

public enum CommonError implements ErrorCode {

    /**
     * unknown exception exposed to the external
     */
    unknown_internal_exception("SYS_0001", "系统内部异常"),

    timeout("SYS_0002", "超时"),

    invalid_param("COMMON_0001", "参数不合法"),

    json_parse_error("COMMON_0002", "JSON解析异常"),

    ;


    private final String code;
    private final String message;

    CommonError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
