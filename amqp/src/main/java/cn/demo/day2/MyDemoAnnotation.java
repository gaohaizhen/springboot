package cn.demo.day2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyDemoAnnotation {

    String color() default "blue";

    String value();

    int[] arrayAttr() default {3, 4, 4};

    cn.demo.day1.TrafficLamp lamp() default cn.demo.day1.TrafficLamp.RED;

    MetaAnnotation annotationAttr() default @MetaAnnotation("lhm");
}
