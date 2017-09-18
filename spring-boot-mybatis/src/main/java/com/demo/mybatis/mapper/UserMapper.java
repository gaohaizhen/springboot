package com.demo.mybatis.mapper;



import com.demo.mybatis.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public List<User> selectUsers(Map<String,Object> param);


    public void addUser(User user);
}
