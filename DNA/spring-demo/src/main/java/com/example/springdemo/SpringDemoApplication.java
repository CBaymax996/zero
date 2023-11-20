package com.example.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringDemoApplication.class, args);

        // 两种方式启动spring
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("xxx.xml");

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(SpringDemoApplication.class);

    }

}
