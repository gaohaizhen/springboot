package com.demo.web.dao;

import com.demo.web.bean.ConfigDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;


@Repository
public class UserDaoIml implements UserDao{

    @Autowired
    public DataSource druidDataSource;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public ConfigDB selectConfig() throws Exception{
       System.out.println( jdbcTemplate.queryForList("select * from config"));

        jdbcTemplate.batchUpdate("Update article set title='hello'");

        //int i=1/0;//出现异常会回滚

        return null;
    }
}
