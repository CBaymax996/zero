package top.cbaymax.matrix.silk.client.layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;


public abstract class AbstractLayer<H extends Handler> {

    public final List<H> chains;

    public AbstractLayer(@Autowired ApplicationContext springContext) {
        @SuppressWarnings("unchecked")
        Class<H> hClass = (Class<H>) Optional.ofNullable(this.getClass())
                .map(Class::getGenericSuperclass)
                .filter(clz -> clz instanceof ParameterizedType)
                .map(clz -> (ParameterizedType) clz)
                .map(ParameterizedType::getActualTypeArguments)
                .map(typeArray -> typeArray.length == 0 ? null : typeArray[0])
                .filter(clz -> clz instanceof Class<?>)
                .filter(clz -> Handler.class.isAssignableFrom((Class<?>) clz))
                .orElse(null);

        Map<String, H> parsers = springContext.getBeansOfType(hClass);

        chains = Optional.of(parsers).map(Map::values).get()
                .stream().filter(Objects::nonNull)
                .sorted(Comparator.comparing(Handler::order))
                .collect(Collectors.toList());
    }

}
