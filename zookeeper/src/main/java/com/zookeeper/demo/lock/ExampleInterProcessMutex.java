package com.zookeeper.demo.lock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * 可重入锁Shared Reentrant Lock
 */
public class ExampleInterProcessMutex {
    private final InterProcessMutex lock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public ExampleInterProcessMutex(CuratorFramework client, String lockPath,
            FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessMutex(client, lockPath);
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " 不能得到互斥锁");
        }
        System.out.println(lock.acquire(time, unit));
        System.out.println(lock.acquire(time, unit));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(lock.acquire(time, unit));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(lock.acquire(time, unit));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        System.out.println(lock.acquire(time, unit));
        try {
            System.out.println(clientName + " 已获取到互斥锁");
            resource.use(); // 使用资源
            Thread.sleep(1000 * 1);
        } finally {
            System.out.println(clientName + " 释放互斥锁");
            lock.release(); // 总是在finally中释放
            lock.release(); // 总是在finally中释放
            lock.release(); // 总是在finally中释放
            lock.release(); // 总是在finally中释放
        }
    }
}
