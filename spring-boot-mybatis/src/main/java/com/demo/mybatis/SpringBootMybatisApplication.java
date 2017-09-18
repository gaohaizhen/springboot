package com.demo.mybatis;

import com.demo.starter.redis.JedisClusterManager;
import com.demo.starter.redis.MyEnableRedis;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;

@SpringBootApplication
@EnableTransactionManagement
@MyEnableRedis
public class SpringBootMybatisApplication {
    //    redis-cluster-servers=192.168.12.85:7000,192.168.12.85:7001,192.168.12.85:7002
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootMybatisApplication.class, args);

        System.out.println(Arrays.asList(context.getEnvironment().getActiveProfiles()));
        //-----------jedisCluster---------------------
        JedisCluster jedisCluster = context.getBean(JedisCluster.class);
        System.out.println(jedisCluster.getSet("wdas", "qwe"));

        //-----------rabbit---------------------
        System.out.println(context.getBean(ConnectionFactory.class));

        CachingConnectionFactory cachingConnectionFactory = (CachingConnectionFactory)context.getBean(ConnectionFactory.class);
        Connection connection = cachingConnectionFactory.createConnection();
        Channel channel = connection.createChannel(true);
//        channel.

    }
}
