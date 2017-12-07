package com.zookeeper.demo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

public class ZookeeperDemo {


    public static void main(String[] args) {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

        /**
         * @ ExponentialBackoffRetry : Retry policy that retries a set number of times with
         * increasing sleep time between retries @ RetryNTimes : Retry policy that retries a max
         * number of times @ RetryOneTime : A retry policy that retries only once @
         * RetryUntilElapsed : A retry policy that retries until a given amount of time elapses
         */

        RetryPolicy retryPolicy1 = new RetryPolicy() {

            public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
                return false;
            }
        };


        // CuratorFramework client =
        // CuratorFrameworkFactory.newClient("192.168.12.85:2181", retryPolicy);
        // client.start();// The client must be started (and closed when no longer needed).

        CuratorFramework client =
                CuratorFrameworkFactory.builder().connectString("192.168.12.85:2181")
                        .retryPolicy(retryPolicy)
                        .sessionTimeoutMs(5000).namespace("base")// '/base'
                        .build();
        client.start();
        try {
            client.blockUntilConnected();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        byte[] myData = "zktestdate".getBytes();
        try {
//            String result = client.create().creatingParentsIfNeeded()
//                    .withMode(CreateMode.PERSISTENT)
//                    .forPath("/my/path", myData);
//            System.out.println(result);
//            System.out.println(new String(client.getData().forPath("/my/path")));
            
            client.delete().guaranteed().deletingChildrenIfNeeded().forPath("/examples");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }


    }
}
