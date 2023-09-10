package top.cbaymax.matrix.silk.client.layer;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;


@Getter
public abstract class AbstractLayer<H extends Handler> {

    private final List<H> chains;

    public AbstractLayer(@Autowired ApplicationContext springContext) {
        // todo 优雅调用
        Class<H> hClass = (Class<H>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Map<String, H> parsers = springContext.getBeansOfType(hClass);
        chains = Optional.of(parsers).map(Map::values).get()
                .stream().filter(Objects::nonNull)
                .sorted(Comparator.comparing(Handler::order))
                .collect(Collectors.toList());
    }


}
