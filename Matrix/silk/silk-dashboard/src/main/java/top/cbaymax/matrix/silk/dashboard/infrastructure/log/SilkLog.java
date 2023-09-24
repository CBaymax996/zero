package top.cbaymax.matrix.silk.dashboard.infrastructure.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 褚浩
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SilkLog {

    /**
     * default All Content {@link LogInfo}
     */
    LogInfo[] includes() default {};

    LogInfo[] excludes() default {};
}
