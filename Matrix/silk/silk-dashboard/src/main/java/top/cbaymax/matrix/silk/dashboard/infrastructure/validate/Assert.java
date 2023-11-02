package top.cbaymax.matrix.silk.dashboard.infrastructure.validate;

import top.cbaymax.matrix.silk.dashboard.infrastructure.error.CommonError;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;

import java.util.Objects;

/**
 * @author 褚浩
 */
public final class Assert {

    @Deprecated
    private Assert() {
    }

    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new SilkError(CommonError.invalid_param, message);
        }
    }

    public static void isNull(Object object, String message) {
        if (Objects.nonNull(object)) {
            throw new SilkError(CommonError.invalid_param, message);
        }
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new SilkError(CommonError.invalid_param, message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new SilkError(CommonError.invalid_param, message);
        }
    }


}
