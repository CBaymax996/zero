package top.cbaymax.matrix.silk.dashboard.infrastructure.error;

public class SilkError extends RuntimeException {

    public String code;

    public String message;

    public SilkError(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public SilkError(ErrorCode errorCode, String customMessage) {
        this.code = errorCode.getCode();
        this.message = customMessage;
    }
}
