package com.demo.web.controller;

import com.demo.web.bean.ResponseDto.ResponseDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.web.bean.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController


//@Controller
//@ResponseBody
@RequestMapping("/user")
public class UserController {

    private static Map<String, Object> data = new HashMap<>();


    /**
     * 局部异常处理，只处理该类里面的指定的异常
     * @return
     */
    @RequestMapping("/getUserInfo.do")
    public ResponseDto getUserInfo() {
        return new ResponseDto().setCode(200).setData(data);
    }


    @RequestMapping("/addUser.do")
    public String addUser(String userName, String pwd) {
        data.put(data.entrySet().size() + 1 + "", new User(userName, pwd));
        ResponseDto<Map<String, Object>> responseDto = new ResponseDto<>();

        responseDto.setData(data);
        System.out.println(data);
        System.out.println(responseDto);

        Gson gson = new Gson();
        return  gson.toJson(responseDto);
    }


    @RequestMapping("/date.do")
    public Date date() {
        System.out.println(new Date());
        return new Date();
    }

    @RequestMapping("/testGlobalExcetion.do")
    public void testGlobalExcetion() {
        throw new NullPointerException();
    }
}
