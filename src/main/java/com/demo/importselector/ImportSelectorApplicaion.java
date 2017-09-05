package com.demo.importselector;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;


@SpringBootApplication

//@ImportResource(locations={"classpath:config/application-beans.xml"})

@Import(MyImportSelectorConfig.class)
public class ImportSelectorApplicaion {
        public static void main(String[] args){
            ConfigurableApplicationContext context = SpringApplication.run(ImportSelectorApplicaion.class,args);

            System.out.println(context.getBeansOfType(Person.class));
            System.out.println(context.getBeansOfType(Biology.class));



            context.close();



        }
}
