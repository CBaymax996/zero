package top.cbaymax.matrix.silk.dashboard.util;


import top.cbaymax.matrix.silk.dashboard.infrastructure.error.CommonError;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;

public class ParamValidator {

    public static void validate(boolean expression, String message) {
        if (!expression) {
            throw new SilkError(CommonError.invalid_param, message);
        }
    }

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new SilkError(CommonError.invalid_param, message);
        }
    }

}
