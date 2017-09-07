package com.demo.web.dao;

import com.demo.web.bean.ConfigDB;

public interface UserDao {

    ConfigDB selectConfig() throws Exception;
}
