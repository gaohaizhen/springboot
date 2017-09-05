package com.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 自己也能读到
 */
@Configuration
@PropertySource(value="classpath:config/jdbc.properties",ignoreResourceNotFound=true)
public class ConfigFromProperties {
    @Value("${jdbcUrl}")
    private String jdbcUrl;
    @Value("${jdbcUserName}")
    private String jdbcUserName;


    @Value("${jdbcPassword}")
    private String jdbcPassword;
    @Value("${jdbcDriverClass}")
    private String jdbcDriverClass;


    @Override
    public String toString() {
        return "ConfigFromProperties{" +
                "jdbcUrl='" + jdbcUrl + '\'' +
                ", jdbcUserName='" + jdbcUserName + '\'' +
                ", jdbcPassword='" + jdbcPassword + '\'' +
                ", jdbcDriverClass='" + jdbcDriverClass + '\'' +
                '}';
    }
}
