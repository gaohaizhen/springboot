package com.zookeeper.demo.znode;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.nodes.PersistentNode;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;

public class PersistentNodeExample {
    private static final String PATH = "/example/ephemeralNode";
    private static final String PATH2 = "/example/node";

    public static void main(String[] args) throws Exception {
        PersistentNode node = null;
        CuratorFramework client = null;
        try {
            client =
                    CuratorFrameworkFactory.newClient("192.168.12.85:2181",
                    new ExponentialBackoffRetry(1000, 3));
            client.getConnectionStateListenable().addListener(new ConnectionStateListener() {

                 @Override
                public void stateChanged(CuratorFramework client, ConnectionState newState) {
                    System.out.println("client state:" + newState.name());

                }
            });
            client.start();

            node = new PersistentNode(client, CreateMode.PERSISTENT, false, PATH,
                    "test".getBytes(Charset.forName("UTF-8")));
            node.start();
            node.waitForInitialCreate(3, TimeUnit.SECONDS);


            // String actualPath = node.getActualPath();
            // System.out.println("node " + actualPath + " value: " + new
            // String(client.getData().forPath(actualPath)));
            // System.out.println("node " + PATH2 + " value: " + new
            // String(client.getData().forPath(PATH2)));
            // System.out.println("node " + actualPath + " doesn't exist: " +
            // (client.checkExists().forPath(actualPath) == null));

            String test = client.create().forPath("/data", "utf-8".getBytes());
            System.out.println(test);
            System.out.println(new String(client.getData().forPath("/data")));
            System.out.println(new String(node.getData(), "utf-8"));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(node);
            CloseableUtils.closeQuietly(client);
        }

    }

}