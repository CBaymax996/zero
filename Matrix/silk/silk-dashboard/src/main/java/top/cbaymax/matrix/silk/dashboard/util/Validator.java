package top.cbaymax.matrix.silk.dashboard.util;


import top.cbaymax.matrix.silk.dashboard.infrastructure.error.CommonError;

public interface Validator {
    void validate(boolean expression, String message);

    default void validate(boolean expression) {
        validate(expression, CommonError.unknown_internal_exception.getMessage());
    }
}
