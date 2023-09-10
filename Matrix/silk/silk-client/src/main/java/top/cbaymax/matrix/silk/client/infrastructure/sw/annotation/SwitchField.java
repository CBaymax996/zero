package top.cbaymax.matrix.silk.client.infrastructure.sw.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置字段
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwitchField {

    @AliasFor("key")
    String value() default "";

    @AliasFor("value")
    String key() default "";

    String desc() default "";
}
