package com.demo;

import com.demo.bean.User;
import com.demo.config.ConfigFromProperties;
import com.demo.config.DateSourceConfig;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 *
 * 配置文件的名字默认为application.properties，默认的位置是classpath根目录或者classpath:/config
 * 也可以另外指定文件系统路径，file:/, file:config/
 * 也可以使用--spring.config.name=来指定（不需要指定文件扩展名）
 * 配置文件路径可以使用--spring.config.location来指定
 * 
 */


/**
 *
 * @EnableAutoConfiguration 自动配置的原理分析:作用是从classpath中搜索
 * 到所有META-INF/spring.factories配置文件，然后将其key即“org.springframework.boot.autoconfigure.EnableAutoConfiguration”的配置项
 * 添加到spring容器；
 *
 * 只有spring.boot.enableautoconfiguration=true（默认为true）时，才启用自动配置
 *
 *@EnableAutoConfiguration 可以启用自动排除功能：一是通过class排除，二是通过class name排除
 *  例如@EnableAutoConfiguration(exclude = {DateSourceConfig.class}, excludeName = {"com.demo.config.DateSourceConfig"})
 *
 *  其内部实现关键点有：
 *      1：ImportSelector 改接口的方法的返回值都被纳入到spring容器中
 *      2：SpringFactoriesLoader 改类可以从classpath中搜索META-INF/spring.factories配置文件，并读取配置
 *
 *
 */

@SpringBootApplication
public class SpringbootApplication {

    private static Logger log = LoggerFactory.getLogger(SpringbootApplication.class);

    @Value("${server.port}")
    private String serverPort;

    @Bean
    @Profile("test")
    public User createUser() {
        return new User("createTestUser");
    }

    @Bean
    @Profile("dev")
    public User createUser2() {
        return new User("createDevUser2");
    }



    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
//        System.out.println(context.getBeanFactory());
//        System.out.println();

//		System.out.println(context.getEnvironment().getSystemProperties());
//		System.out.println(context.getEnvironment().getPropertySources());
//		context.setEnvironment(ConfigurableEnvironment);
        Environment env = context.getEnvironment();

        System.out.println(env.getProperty("server.port"));
        System.out.println(Arrays.asList(env.getActiveProfiles()));

        System.out.println(env.resolvePlaceholders("${server.port}"));

        System.out.println(context.getBean(DateSourceConfig.class).toString());
        System.out.println(context.getBean(ConfigFromProperties.class));



        System.out.println("-------------------------------");


//        System.out.println(context.getBean(ReadConfigFromProperties.class));
        System.out.println(context.getBean(User.class));
        System.out.println(context.getBeansOfType(Runnable.class));
        System.out.println(context.getBeansOfType(Gson.class));

        System.out.println("-------------------------------");


        log.info(""+context.getBeansOfType(MyRedis.class));

        log.info(""+context.getBeansOfType(MyRabbitmq.class));

        context.close();
    }
}
