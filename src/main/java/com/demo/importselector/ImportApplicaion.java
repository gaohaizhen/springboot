package com.demo.importselector;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @ {@link Import} 用来导入一个或者对个class，导入后会被spring容器管理并实例化为bean；也可以倒是一个注解了 @ {@link Configuration} 的class，
 *
 * 其内部的注解了@Bean的方法也会生成spring容器管理的bean
 */
//@SpringBootApplication
//@Import({Person.class,Biology.class,MyImportConfig.class})
public class ImportApplicaion {
        public static void main(String[] args){
            ConfigurableApplicationContext context = SpringApplication.run(ImportApplicaion.class,args);

            System.out.println(context.getBeansOfType(Person.class));
            System.out.println(context.getBeansOfType(Biology.class));
            context.close();


        }
}
