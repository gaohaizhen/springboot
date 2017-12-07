package com.zookeeper.demo.leader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import com.google.common.collect.Lists;


/***
 * @INTRO 必须启动LeaderLatch: leaderLatch.start();一旦启动，LeaderLatch会和其它使用相同latch
 *        path的其它LeaderLatch交涉，然后随机的选择其中一个作为leader。
 * 
 *        一旦不使用LeaderLatch了，必须调用close方法。如果它是leader,会释放leadership，其它的参与者将会选举一个leader。
 * 
 * 
 * 
 * @DESC
 * 
 *       首先我们创建了10个LeaderLatch，启动后它们中的一个会被选举为leader。因为选举会花费一些时间，start后并不能马上就得到leader。
 * 
 *       通过hasLeadership查看自己是否是leader，如果是的话返回true。
 * 
 *       可以通过.getLeader().getId()可以得到当前的leader的ID。
 * 
 *       只能通过close释放当前的领导权。
 * 
 *       await是一个阻塞方法， 尝试获取leader地位，但是未必能上位。
 * 
 *       注意：LeaderLatch类不能close()多次，LeaderLatch.hasLeadership()与LeaderLatch.getLeader()得到的结果不一定一致，
 *       需要通过LeaderLatch.getLeader().isLeader()来判断。
 * 
 *
 *
 * @see LeaderLatch实例可以增加ConnectionStateListener来监听网络连接问题。当 SUSPENDED 或 LOST
 *      时,leader不再认为自己还是leader.当LOST 连接重连后 RECONNECTED,LeaderLatch会删除先前的ZNode然后重新创建一个.
 *      LeaderLatch用户必须考虑导致leadershi丢失的连接问题。强烈推荐你使用ConnectionStateListener。
 */
public class LeaderLatchExample {
    private static final int CLIENT_QTY = 10;
    private static final String PATH = "/examples/leader";

    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();
        try {
            for (int i = 0; i < CLIENT_QTY; ++i) {
                CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.12.85:2181",
                        new ExponentialBackoffRetry(1000, 3));
                clients.add(client);
                client.start();
                LeaderLatch example = new LeaderLatch(client, PATH, "Client #" + i);
                examples.add(example);
                example.start();
            }
            System.out.println("LeaderLatch初始化完成！");
            Thread.sleep(10 * 1000);// 等待Leader选举完成
            LeaderLatch currentLeader = null;
            for (int i = 0; i < CLIENT_QTY; ++i) {
                LeaderLatch example = examples.get(i);
                if (example.hasLeadership()) {
                    currentLeader = example;
                }
            }
            System.out.println("当前leader：" + currentLeader.getId());
            currentLeader.close();
            examples.get(0).await(10, TimeUnit.SECONDS);
            System.out.println("当前leader：" + examples.get(0).getLeader());
            System.out.println("输入回车退出");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (LeaderLatch exampleClient : examples) {
                System.out.println("当前leader：" + exampleClient.getLeader());
                try {
                    CloseableUtils.closeQuietly(exampleClient);
                } catch (Exception e) {
                    System.out.println(exampleClient.getId() + " -- " + e.getMessage());
                }
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
        System.out.println("OK!");
    }
}
