package com.demo.enable;


import com.demo.importselector.MyImportSelectorConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportSelectorConfig.class)
public @interface EnableLog{

    String value() default "EnableLog";
}