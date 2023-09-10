package top.cbaymax.matrix.silk.client.infrastructure.sw;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.cbaymax.matrix.silk.infrastructure.sw.annotation.SwitchConfig;
import top.cbaymax.matrix.silk.infrastructure.sw.annotation.SwitchField;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Component
public class SwitchListener {


    private final Map<String, Field> fields;

    @Autowired
    public SwitchListener(ApplicationContext context) {
        fields = CollectionUtils.newHashMap(16);
        Map<String, Object> configBeans = context.getBeansWithAnnotation(SwitchConfig.class);
        configBeans.forEach(this::parseField);
    }

    private void parseField(String beanName, Object configBean) {
        if (Objects.isNull(configBean)) {
            return;
        }
        Class<?> configClass = configBean.getClass();
        Field[] fields = configClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(SwitchField.class)) {
                continue;
            }
            SwitchField annotation = AnnotationUtils.getAnnotation(field, SwitchField.class);
            assert annotation != null;
            String fieldKey = Stream.of(annotation.value(), annotation.key()).filter(StringUtils::hasText)
                    .findAny().orElse(null);
            if (this.fields.containsKey(fieldKey)) {
                throw new RuntimeException("配置扫描异常，重复Key值：key:" + fieldKey);
            }
            this.fields.put(fieldKey, field);
        }
        log.info("switch scan fields {}",
                Optional.ofNullable(this.fields).map(Map::keySet).orElse(Collections.emptySet()));
    }
}
