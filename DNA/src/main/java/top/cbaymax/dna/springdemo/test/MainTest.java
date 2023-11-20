package top.cbaymax.dna.springdemo.test;

import top.cbaymax.dna.springdemo.spring.annotation.AnnotationConfigApplicationContext;
import top.cbaymax.dna.springdemo.spring.annotation.ComponentScan;
import top.cbaymax.dna.springdemo.spring.annotation.Scope;

/**
 * @author 褚浩
 */
@ComponentScan("top.cbaymax.dna.springdemo.test.service")
@Scope("protype")
public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainTest.class);
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));

    }
}