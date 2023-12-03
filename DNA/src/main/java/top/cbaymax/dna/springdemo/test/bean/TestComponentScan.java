package top.cbaymax.dna.springdemo.test.bean;

import top.cbaymax.dna.springdemo.spring.annotation.AnnotationConfigApplicationContext;
import top.cbaymax.dna.springdemo.test.MainTest;
import top.cbaymax.dna.springdemo.test.demo.UserService;

/**
 * @author 褚浩
 */
public class TestComponentScan {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainTest.class);
        System.out.println(applicationContext.getBean("userService", UserService.class));
        System.out.println(applicationContext.getBean("userService", UserService.class));
        System.out.println(applicationContext.getBean("userService", UserService.class));
        System.out.println(applicationContext.getBean("userService", UserService.class));

    }
}
