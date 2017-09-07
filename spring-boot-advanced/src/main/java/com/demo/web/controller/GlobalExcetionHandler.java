package com.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理,处理指定的异常--@ExceptionHandler(Exception.class)
 */
//@ControllerAdvice("com.demo")
//@ResponseBody
@RestControllerAdvice("com.demo")
public class GlobalExcetionHandler {

    @ExceptionHandler(Exception.class)
    public String handlerGlobalExcetion(Exception e){
        return e.getClass().getName();
    }
}
