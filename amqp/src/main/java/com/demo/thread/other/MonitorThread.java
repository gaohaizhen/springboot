package com.demo.thread.other;

import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MonitorThread {

    public static void main(String[] args){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        threadMXBean.setThreadContentionMonitoringEnabled(false);

//        threadMXBean.

        for(long threadId : threadMXBean.getAllThreadIds()){
            System.out.println(threadId);
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
            System.out.println(threadInfo.getThreadName()+"---"+threadInfo.getThreadState().toString()+"---"+threadInfo);
            StackTraceElement[]  stackTraceElements = threadInfo.getStackTrace();
            for(StackTraceElement stackTraceElement: stackTraceElements){
                System.out.println(stackTraceElement.toString());
            }

            MonitorInfo[] monitorInfos = threadInfo.getLockedMonitors();
            for (MonitorInfo monitorInfo : monitorInfos){
                System.out.println(monitorInfo);
            }
        }
    }
}
