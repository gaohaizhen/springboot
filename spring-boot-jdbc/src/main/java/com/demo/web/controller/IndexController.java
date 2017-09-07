package com.demo.web.controller;


import com.demo.web.bean.ConfigDB;
import com.demo.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/index.do")
    public String index(){
        return "/index";
    }

    @RequestMapping("/home.do")
    @ResponseBody
    public String home(){
        return "home";
    }


    @RequestMapping("/selectConfig.do")
    @ResponseBody
    public ConfigDB selectConfig() throws Exception{
        System.out.println(userDao);
        return userDao.selectConfig();
    }
}
