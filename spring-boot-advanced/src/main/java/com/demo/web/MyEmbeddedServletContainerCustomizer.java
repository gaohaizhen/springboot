package com.demo.web;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * 订制tomcat容器的方式，实现EmbeddedServletContainerCustomizer@customize(ConfigurableEmbeddedServletContainer container)方法
 *
 * 和在application.properties中配置等效
 */
@Component
public class MyEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer {
    /**
     * Customize the specified {@link ConfigurableEmbeddedServletContainer}.
     *
     * @param container the container to customize
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
//        container.setContextPath("/customer");
    }
}
