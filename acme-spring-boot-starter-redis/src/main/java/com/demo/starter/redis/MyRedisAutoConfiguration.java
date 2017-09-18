package com.demo.starter.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Scope;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ConditionalOnClass({Jedis.class, JedisCluster.class})
@EnableConfigurationProperties(RedisProperties.class)
public class MyRedisAutoConfiguration {

    private static final int connectionTimeout = 1000;
    private static final int maxAttempts = 20;

    private static final int MAX_TOTAL = 100;
    private static final int MAX_IDLE = 40;
    private static final int MIN_IDLE = 20;
    private static final int MAX_WAITEMILLIS = 30000;


    @Value("${redis-cluster-servers}")
    private String redisServers;


    @Bean
    @ConditionalOnMissingBean(Jedis.class)
    @Scope("singleton")
    public Jedis jedis(RedisProperties redisProperties) {
        return new Jedis(redisProperties.getHost(), redisProperties.getPort());
    }

    @Bean
    @ConditionalOnMissingBean(JedisCluster.class)
    @Scope("singleton")
    public JedisCluster jedisCluster() {
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        for (String redisServer : redisServers.split("[,]")) {
            String[] sa = redisServer.split("[:]");
            if (sa.length == 2) {
                String host = sa[0];
                int port = Integer.parseInt(sa[1]);
                jedisClusterNode.add(new HostAndPort(host, port));
            }
        }

        System.out.println(redisServers);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(MAX_TOTAL);
        jedisPoolConfig.setMaxIdle(MAX_IDLE);
        jedisPoolConfig.setMinIdle(MIN_IDLE);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setMaxWaitMillis(MAX_WAITEMILLIS);


        return new JedisCluster(jedisClusterNode, connectionTimeout, maxAttempts, jedisPoolConfig);
    }


}
