package com.demo.starter.redis;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyRedisAutoConfiguration.class)
public @interface MyEnableRedis {
}
