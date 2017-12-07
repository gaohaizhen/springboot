package zoo;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

public class Zoo01 {

    public static void main(String[] args) throws IOException {

        String hostPort = "localhost:2181";
        int sessionTimeout = 3000;
        String path = "/test/app1";

        ZooKeeper zk = new ZooKeeper(hostPort, sessionTimeout, new Watcher() {

            public void process(WatchedEvent event) {
                String path = event.getPath();
                System.out.println("watch:" + event.getType() + ",path:" + path);

            }
        });
        States states = zk.getState();
        String statesName = states.name();
        System.out.println(statesName);
        
        Boolean isAlive = states.isAlive();
        System.out.println(isAlive);
        
        try {
            zk.create("/zk/test", "hello".getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
