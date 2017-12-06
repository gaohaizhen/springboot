package com.demo.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * zookeeper基于ZAB协议实现
 * <p>
 * 1.应用之一：数据发布与订阅（pub/sub有推和拉两种模式，zk采用二者相结合的模式：服务器更新数据后，向客户端发送Wacther事件，客户端收到Wacther时间后向服务器拉去数据）
 * 实现配置配置信息的集中式管理和数据的动态更新
 * <p>
 * 此类数据共有特点：1>数据量小，2>在运行时数据内容会发生动态变化，3>集群中各机器共享，配置一致
 * <p>
 * 另外配置数据的方式还有本地配置文件方式（定时取）和内存变量方式（JMX方式）
 */
public class Pub_Sub {

    public static void main(String args[]) {
        String zookeeperConnectionString = "192.168.12.85:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();

    }
}
