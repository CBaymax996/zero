package top.cbaymax.matrix.silk.dashboard.infrastructure.enumeration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 褚浩
 */
class EnumContainer {

    private final static Map<IBaseEnum<?>, EnumDefinition<?>> enum_definitions = new ConcurrentHashMap<>();

    protected static <T> void putEnum(IBaseEnum<T> baseEnum, T code, String msg) {
        enum_definitions.put(baseEnum, new EnumDefinition<>(code, msg));
    }

    @SuppressWarnings("unchecked")
    static <K extends IBaseEnum<T>, T> EnumDefinition<T> getEnumDefinition(K key) {
        return (EnumDefinition<T>) (enum_definitions.get(key));
    }

    record EnumDefinition<C>(C code, String msg) {
    }
}

