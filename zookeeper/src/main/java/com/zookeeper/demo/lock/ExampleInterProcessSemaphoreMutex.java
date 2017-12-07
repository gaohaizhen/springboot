package com.zookeeper.demo.lock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

/**
 * 不可重入锁Shared Lock
 * 
 * 这个锁和上面的相比，就是少了Reentrant的功能，也就意味着它不能在同一个线程中重入。这个类是InterProcessSemaphoreMutex使用方法和上面的类类似
 * 首先我们将上面的例子修改一下，测试一下它的重入。修改ExampleClientThatLocks.doWork,连续两次acquire:
 * 
 * 
 * 注意：我们也需要调用release两次。这和JDK的ReentrantLock用法一致。如果少调用一次release，则此线程依然拥有锁。
 * 
 * 上面的代码没有问题，我们可以多次调用acquire，后续的acquire也不会阻塞。
 * 
 * 但是将上面的InterProcessMutex换成不可重入锁InterProcessSemaphoreMutex,如果再运行上面的代码，结果就会发现线程被阻塞在第二个acquire上，直到超时。也就是此锁不是可重入的。
 */
public class ExampleInterProcessSemaphoreMutex {
    private final InterProcessSemaphoreMutex lock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public ExampleInterProcessSemaphoreMutex(CuratorFramework client, String lockPath,
            FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessSemaphoreMutex(client, lockPath);
    }

    public void doWork(long time, TimeUnit unit) throws Exception {
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " 不能得到互斥锁");
        }
        System.out.println(clientName + " 已获取到互斥锁");
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " 不能得到互斥锁");
        }
        System.out.println(clientName + " 再次获取到互斥锁");
        try {
            resource.use(); // 使用资源
            Thread.sleep(1000 * 1);
        } finally {
            System.out.println(clientName + " 释放互斥锁");
            lock.release(); // 总是在finally中释放
            lock.release(); // 获取锁几次 释放锁也要几次
        }
    }

}
