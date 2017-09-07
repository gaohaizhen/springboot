package com.demo.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 不指定MyApplicationListenr监听器要监听的具体事件，则MyApplicationListenr监听器就会监听全部事件，
 * 包括spring启动过程中的所有时间和自定义事件
 */
public class MyApplicationListenr implements ApplicationListener {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("---MyApplicationListenr---"+event.toString());
    }
}
