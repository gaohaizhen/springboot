package com.zookeeper.demo.util;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.utils.EnsurePath;

/**
 * EnsurePath提供了一种确保数据节点存在的机制（上层业务操作之前需要确保该节点存在 ，而EnsurePath可以免去创建节点时节点是否存在的判断）
 *
 */
public class EnsurePath_demo {

    static String path = "/ensurePath";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.12.85:2181").sessionTimeoutMs(5000)
            .retryPolicy(new RetryPolicy() {
                public boolean allowRetry(int retryCount, long elapsedTimeMs,
                        RetrySleeper sleeper) {
                    try {
                        System.out.println("retryCount=" + retryCount + ";" + "elapsedTimeMs="
                                + elapsedTimeMs);
                        sleeper.sleepFor(10, TimeUnit.SECONDS);
                        if (retryCount > 100) {
                            return false;// 重试100次后，不在重试
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }).build();


    public static void main(String[] args) throws Exception {

        client.start();
        client.blockUntilConnected();

        client.usingNamespace("zk");

        // Since 2.9.0 - Prefer CuratorFramework.create().creatingParentContainersIfNeeded() or
        // CuratorFramework.exists().creatingParentContainersIfNeeded()
        EnsurePath ensurePath = new EnsurePath(path);
        client.create().creatingParentContainersIfNeeded().forPath(path);
        client.checkExists().creatingParentContainersIfNeeded().forPath(path);
    }

}
