package com.demo.mybatis.service;

import com.demo.mybatis.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {


    public List<User> selectUsers(Map<String,Object> param);

    public void addUser(User user);
}
