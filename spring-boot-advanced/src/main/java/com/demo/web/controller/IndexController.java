package com.demo.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 在springboot中使用jsp，需要在classpath中引入tomcat-embed-jasper的依赖
 */


@Controller

public class IndexController {


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String error(Exception e) {

        return "--IndexController--error--ExceptionHandler--"+e.getClass().getName();
    }

    @RequestMapping("index.do")
    public String index() {

        return "/index";
    }

    @RequestMapping("exceptionHandler.do")
    @ResponseBody
    public String exceptionHandler() {
        throw new IllegalArgumentException();
    }


    @RequestMapping("login.do")
    public String login(Model model) {
        model.addAttribute("name", "zhangsan");
        return "login";
    }


    @RequestMapping("register.do")
    public String register(Model model) {
        model.addAttribute("msg", "注册成功");
        return "register";
    }

}
