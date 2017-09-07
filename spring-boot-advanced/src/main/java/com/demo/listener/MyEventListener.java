package com.demo.listener;

import org.springframework.context.ApplicationListener;

/**
 * 指定该监听器只监听的时间类型为MyApplicationEvent
 */
public class MyEventListener implements ApplicationListener<MyApplicationEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {

        System.out.println("---MyEventListener----"+event);


    }
}
