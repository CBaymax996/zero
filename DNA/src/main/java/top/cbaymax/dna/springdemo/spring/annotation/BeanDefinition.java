package top.cbaymax.dna.springdemo.spring.annotation;

/**
 * @author 褚浩
 */
public class BeanDefinition {

    private final Class<?> aClass;
    private final String beanName;
    private final ScopeType scope;

    public BeanDefinition(String beanName, Class<?> aClass, ScopeType scope) {
        this.beanName = beanName;
        this.aClass = aClass;
        this.scope = scope;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public ScopeType getScope() {
        return scope;
    }

}
