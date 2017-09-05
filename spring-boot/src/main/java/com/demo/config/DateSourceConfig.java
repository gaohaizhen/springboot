package com.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 从application.properties中读
 */

@Component
public class DateSourceConfig {

    @Value("${url}")
    private String url;

    @Value("${name}")
    private String name;

    @Value("${password}")
    private String password;

    @Value("${classname}")
    private String classname;


    @Override
    public String toString() {
        return "DateSourceConfig{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
