package com.demo.web;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.demo.web.dao..*.*(..))")
    public void printLog(){

        System.out.println("----LogAspect#printLog()----");
    }
}
