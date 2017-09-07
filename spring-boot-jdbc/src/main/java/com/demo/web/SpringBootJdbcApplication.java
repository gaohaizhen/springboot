package com.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.demo.starter.redis.MyEnableRedis;

import redis.clients.jedis.Jedis;


/**
 * AOP开发流程：
 * 		1：加入spring-boot-starter-aop的依赖，默认就开启了Aop的支持
 * 		2：写一个aspect，封装横切关注点（日志，监控等），然后配置通知（前置通知，后置通知等）和切入点（哪些类、哪些方法）
 * 		3：这个aspect需要加上@Aspect和@Component，纳入到spring容器中
 *
 *
 *
 *
 *
 *
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@MyEnableRedis
public class SpringBootJdbcApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringBootJdbcApplication.class);

	public static void main(String[] args) {


		ConfigurableApplicationContext context = SpringApplication.run(SpringBootJdbcApplication.class, args);
        System.out.println(context.getBean(Jedis.class));

		Jedis jedis = context.getBean(Jedis.class);
        // jedis.set("id123","123");

        System.out.println(jedis.keys("*"));




	}
}
