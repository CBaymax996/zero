package top.cbaymax.dna.springdemo.test.demo;

import top.cbaymax.dna.springdemo.spring.annotation.BeanPostProcessor;

/**
 * @author 褚浩
 */
public class MyBeanPosterProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Class<?> beanClass, String beanName) {
        System.out.println("after");
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        System.out.println("before");
        return null;
    }
}
