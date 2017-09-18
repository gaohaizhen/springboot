package com.demo.mybatis.service.impl;

import com.demo.mybatis.bean.User;
import com.demo.mybatis.mapper.UserMapper;
import com.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectUsers(Map<String, Object> param) {
        return userMapper.selectUsers(param);
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
