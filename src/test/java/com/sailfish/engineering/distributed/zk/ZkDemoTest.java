package com.sailfish.engineering.distributed.zk;

import com.github.zkclient.IZkChildListener;
import com.github.zkclient.IZkClient;
import com.github.zkclient.IZkDataListener;
import com.github.zkclient.ZkClient;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author sailfish
 * @create 2019-10-25-11:40
 */
public class ZkDemoTest {


    @Test
    public void test_zk_api() throws IOException, KeeperException, InterruptedException {
        final String conn = "58.87.97.129:2181";
        final String path = "/com.sailfishc.cn";
//        ZooKeeper zk = new ZooKeeper(conn, 3, new Watcher() {
//
//            @Override
//            public void process(WatchedEvent event) {
//                System.out.println("WatchEvent: WatchPath=" + event.getPath() + ", WatchState=" + event.getState());
//            }
//        });

        IZkClient zkClient = new ZkClient(conn);
        if (zkClient.exists(path)) {
            zkClient.delete(path);
        } else {
            System.out.println("Path:" + path + " is empty");
        }

        zkClient.createPersistent(path);
        
        zkClient.writeData(path, "sailfish".getBytes());

        final byte[] bytes = zkClient.readData(path, true);
        System.out.println("get Data=" + new String(bytes));

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, byte[] data) throws Exception {
                System.out.println("dataPath " + dataPath + "is update, data=" + new String(data));
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("dataPath " + dataPath + "is delete");
            }
        });

        zkClient.delete(path);

        TimeUnit.SECONDS.sleep(10);
    }
}