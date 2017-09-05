package com.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfiguration {

    @Bean
    public MyRabbitmq CreateMyRabbitmq() {
        return new MyRabbitmq();
    }

}
