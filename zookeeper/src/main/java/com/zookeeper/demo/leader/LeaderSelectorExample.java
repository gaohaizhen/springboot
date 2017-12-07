package com.zookeeper.demo.leader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import com.google.common.collect.Lists;

/**
 * 你可以在takeLeadership方法中进行任务的分配等业务处理，并且不要返回(一返回就会释放Leader权)，
 * 如果你想要要此实例一直是leader的话可以加一个死循环。leaderSelector.autoRequeue();保证在此实例释放领导权之后还可能获得领导权。
 * 在这里我们使用AtomicInteger来记录此client获得领导权的次数，每个client有平等的机会获得领导权。
 * 
 */
public class LeaderSelectorExample {
    private static final int CLIENT_QTY = 10;
    private static final String PATH = "/examples/leader";

    public static void main(String[] args) throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<ExampleClient> examples = Lists.newArrayList();
        try {
            for (int i = 0; i < CLIENT_QTY; ++i) {
                CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.12.85:2181",
                        new ExponentialBackoffRetry(1000, 3));
                clients.add(client);
                client.start();
                ExampleClient example = new ExampleClient(client, PATH, "Client #" + i);
                examples.add(example);
                example.start();
            }
            System.out.println("输入回车退出：");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } finally {
            for (ExampleClient exampleClient : examples) {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
        System.out.println("OK!");
    }
}

/**
 * result:
 * 可以看出：LeaderSelector与LeaderLatch的区别，通过LeaderSelectorListener可以对领导权进行控制，在适当的时候释放领导权，这样每个节点都有可能获得领导权。
 * 而LeaderLatch一根筋到死，除非调用close方法，否则它不会释放领导权。
 */
