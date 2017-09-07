package com.demo;


import com.demo.listener.MyApplicationEvent;
import com.demo.listener.MyApplicationListenr;
import com.demo.listener.MyEventListener;
import com.demo.web.interceptor.MyHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 一：事件监听器
 * 1：自定义事件，一般是继承ApplicEvent抽象类
 * 2：定义时间监听器，一般是实现ApplicationListener接口
 * 3：启动时把监听器加入到spring容器中
 * 4：使用ApplicationContext.publishEvent发布事件
 *
 * 5:实现代码
 *      springApplication.addListeners(new MyApplicationListenr(), new MyEventListener());//可以添加多个监听器
 *
 *      ConfigurableApplicationContext context = springApplication.run(args);
 *
 *      //context.addApplicationListener(new MyApplicationListenr());
 *      context.publishEvent(new MyApplicationEvent(new Object()));
 *
 *
 *
 *
 *
*/

@ServletComponentScan
@SpringBootApplication
public class App extends WebMvcConfigurerAdapter{


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new MyHandlerInterceptor());
    }

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(App.class);
//        springApplication.addListeners(new MyApplicationListenr(), new MyEventListener());//可以添加多个监听器

        ConfigurableApplicationContext context = springApplication.run(args);

//        context.addApplicationListener(new MyApplicationListenr());
//        context.publishEvent(new MyApplicationEvent(new Object()));






//        context.close();

    }
}
