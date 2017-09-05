package com.demo.enable;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportBeanDefinitionRegister.class)
public @interface EnableLog2 {
    String name() default "EnableLog2";
}
