package com.zookeeper.demo.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;


public class InterProcessMutexExample {
    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 10;
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        final List<CuratorFramework> clientList = new ArrayList<CuratorFramework>();
        for (int i = 0; i < QTY; i++) {
            CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.12.85:2181",
                    new ExponentialBackoffRetry(1000, 3));
            client.start();
            // curator.getConnectionStateListenable().addListener(stateListener);
            client.getConnectionStateListenable()
                    .addListener(new MyConnectionStateListener(PATH, PATH));;
            clientList.add(client);
        }



        System.out.println("连接初始化完成！");

        ExecutorService service = Executors.newFixedThreadPool(QTY);
        for (int i = 0; i < QTY; ++i) {
            final int index = i;
            Callable<Void> task = new Callable<Void>() {
                // @Override
                public Void call() throws Exception {
                    try {
                        final ExampleInterProcessMutex example = new ExampleInterProcessMutex(
                                clientList.get(index), PATH, resource, "Client " + index);
                        // ExampleClientThatLocks
                        // ExampleInterProcessSemaphoreMutex
                        // ExampleInterProcessReadWriteLock
                        // ConnectionStateListener
                        for (int j = 0; j < REPETITIONS; ++j) {
                            example.doWork(10, TimeUnit.SECONDS);
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    } finally {
                        CloseableUtils.closeQuietly(clientList.get(index));
                    }
                    return null;
                }
            };
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("OK!");
    }
}
