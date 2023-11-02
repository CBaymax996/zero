package top.cbaymax.matrix.silk.dashboard.infrastructure.error;

public class SilkError extends RuntimeException {

    private String code;

    private String message;

    private SilkError() {
    }

    private SilkError(Throwable throwable) {
        super(throwable);
    }

    public SilkError(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public SilkError(ErrorCode errorCode, String customMessage) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage() + ": " + customMessage;
    }

    public static SilkErrorBuilder builder(ErrorCode code) {
        return new SilkErrorBuilder(code);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static class SilkErrorBuilder {

        private final ErrorCode code;

        private String message;

        private Throwable throwable;

        public SilkErrorBuilder(ErrorCode code) {
            this.code = code;
        }


        public SilkErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public SilkErrorBuilder error(Throwable t) {
            this.throwable = t;
            return this;
        }

        public SilkError build() {
            SilkError silkError = this.throwable == null ? new SilkError() : new SilkError(throwable);
            silkError.code = this.code.getCode();
            silkError.message = this.message == null ? this.code.getMessage() : this.code.getMessage() + ": " + this.message;
            return silkError;
        }
    }

}
