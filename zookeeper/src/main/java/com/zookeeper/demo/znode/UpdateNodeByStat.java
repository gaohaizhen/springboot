package com.zookeeper.demo.znode;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.data.Stat;

public class UpdateNodeByStat {
    public static void main(String[] args) {

        RetryPolicy retryPolicy = new RetryOneTime(1000);
        
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.12.85:2181").retryPolicy(retryPolicy).namespace("update")
                .build();
        client.start();
        try {
            client.blockUntilConnected();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        try {
            client.create().creatingParentsIfNeeded().forPath("/update/mydata", "data".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Stat stat = new Stat();
        try {
            String data =
                    new String(client.getData().storingStatIn(stat).forPath("/update/mydata"));// 相对命名空间‘update’-->/update/update/mydata
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Stat forPath = client.setData().withVersion(stat.getAversion())
                    .forPath("/update/mydata", "updatedata".getBytes());
            String updatedata =
                    new String(client.getData().storingStatIn(stat).forPath("/update/mydata"));
            System.out.println(updatedata);

            // KeeperErrorCode = BadVersion for /update/update/mydata
            Stat forPath2 = client.setData().withVersion(stat.getAversion())
                    .forPath("/update/mydata", "updatedata".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
