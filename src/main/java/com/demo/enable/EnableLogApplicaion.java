package com.demo.enable;


import com.demo.importselector.Biology;
import com.demo.importselector.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



@EnableLog("EnableLog")
@SpringBootApplication
public class EnableLogApplicaion {
        public static void main(String[] args){

            ConfigurableApplicationContext context = SpringApplication.run(EnableLogApplicaion.class,args);

            System.out.println(context.getBeansOfType(Person.class));
            System.out.println(context.getBeansOfType(Biology.class));



            context.close();



        }
}
