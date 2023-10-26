package top.cbaymax.matrix.silk.dashboard.infrastructure.enumeration;

import java.util.stream.Stream;

/**
 * @author 褚浩
 */
public interface IBaseEnum<CODE> {

    static <C, E extends IBaseEnum<C>> E getByCode(Class<E> clz, C code) {
        return Stream.of(clz.getEnumConstants())
                .filter(enumBean -> enumBean.getCode().equals(code))
                .findAny()
                .orElse(null);
    }

    default void init(CODE code, String msg) {
        EnumContainer.putEnum(this, code, msg);
    }

    default String getMessage() {
        return EnumContainer.getEnumDefinition(this).msg();
    }

    default CODE getCode() {
        return EnumContainer.getEnumDefinition(this).code();
    }


}
