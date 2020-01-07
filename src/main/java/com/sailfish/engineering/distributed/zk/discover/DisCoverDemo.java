package com.sailfish.engineering.distributed.zk.discover;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author sailfish
 * @create 2019-10-25-15:14
 */
public class DisCoverDemo {

    private static final String PATH = "/discovery/example";
    private static final String CONNECTION_INFO = "58.87.97.129:2181";

    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(CONNECTION_INFO)
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .namespace("base")
                        .build();

        client.start();

        client.create().forPath("path");


    }
}
