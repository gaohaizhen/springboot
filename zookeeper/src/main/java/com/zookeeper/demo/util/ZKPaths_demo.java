package com.zookeeper.demo.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.curator.utils.ZKPaths.PathAndNode;
import org.apache.zookeeper.ZooKeeper;

public class ZKPaths_demo {

    static String path = "/curator_zkpath";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.12.85:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .sessionTimeoutMs(5000).build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.blockUntilConnected();

        ZooKeeper zookeeper = client.getZookeeperClient().getZooKeeper();
        // ZKPaths.mkdirs(zookeeper, path);
        System.out.println(ZKPaths.fixForNamespace(path, "sub"));
        System.out.println(ZKPaths.makePath(path, "sub1"));
        PathAndNode pathAndNode = ZKPaths.getPathAndNode(path);
        System.out.println(pathAndNode.getNode());
        System.out.println(pathAndNode.getPath());



    }

}
