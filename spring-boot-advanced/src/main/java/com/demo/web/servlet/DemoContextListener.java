package com.demo.web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DemoContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("---contextInitialized---");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        System.out.println("---contextDestroyed---");

    }
}
