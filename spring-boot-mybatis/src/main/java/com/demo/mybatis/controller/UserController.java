package com.demo.mybatis.controller;

import com.demo.mybatis.bean.User;
import com.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("selectUsers.do")
    public List<User> selectUsers(){
        Map<String, Object> param = new HashMap<>();
        param.put("name","123");
        List<User> users = userService.selectUsers(param);

        return users;
    }
}
