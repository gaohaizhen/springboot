package com.demo.condition;

import com.google.gson.Gson;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class MyConditionOn {

    /**
     * 表示某个属性等于某个值才会装配
     * @return
     */
    @Bean
    @ConditionalOnProperty(name="runnable.enable", havingValue = "true")
    public Runnable createRunnable(){
        return ()->{};
    }

    /**
     * 表示classpath中存在某个class时才会装配
     * @return
     */
    @Bean
    @ConditionalOnClass(Gson.class)
    public Gson createGson(){
        return new Gson();

    }
}
