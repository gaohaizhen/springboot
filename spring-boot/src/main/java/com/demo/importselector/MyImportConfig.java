package com.demo.importselector;


import org.springframework.context.annotation.Bean;

public class MyImportConfig {

    @Bean
    public Person createPerson(){
        return  new Person();
    }

    @Bean
    public Biology createBiology(){
        return  new Biology();
    }
}
