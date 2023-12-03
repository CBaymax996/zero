package top.cbaymax.dna.springdemo.spring.annotation;

/**
 * @author 褚浩
 */
public interface BeanPostProcessor {
    Object postProcessAfterInitialization(Class<?> beanClass, String beanName) throws Exception;

    // InstantiationAwareBeanPostProcessor
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws Exception;
}
