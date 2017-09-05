package com.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ConfigFromProperties自己也能读到这些值
 */
//@Component
public class ReadConfigFromProperties {

    @Value("${jdbcUrl}")
    private String jdbcUrl;
    @Value("${jdbcUserName}")
    private String jdbcUserName;
    @Value("${jdbcPassword}")
    private String jdbcPassword;
    @Value("${jdbcDriverClass}")
    private String jdbcDriverClass;

    @Value("${jdbcDriverClass2}")
    private String jdbcDriverClass2;

    @Override
    public String toString() {
        return "ReadConfigFromProperties{" +
                "jdbcUrl='" + jdbcUrl + '\'' +
                ", jdbcUserName='" + jdbcUserName + '\'' +
                ", jdbcPassword='" + jdbcPassword + '\'' +
                ", jdbcDriverClass='" + jdbcDriverClass + '\'' +
                ", jdbcDriverClass2='" + jdbcDriverClass2 + '\'' +
                '}';
    }
}
