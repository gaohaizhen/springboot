package com.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfiguration {


    @Bean
    public MyRedis createMyRedis(){
        return new MyRedis();
    }

}
