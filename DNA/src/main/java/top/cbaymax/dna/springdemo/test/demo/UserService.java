package top.cbaymax.dna.springdemo.test.demo;

import top.cbaymax.dna.springdemo.spring.annotation.*;

/**
 * @author 褚浩
 */
@Component("userService")
@Scope(ScopeType.prototype)
public class UserService implements BeanNameAware, InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet init");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    public void testAutowired() {
        System.out.println(orderService);
    }
}
