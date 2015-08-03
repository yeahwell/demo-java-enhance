package com.yeahwell.demo.zookeeper.configcenter;


import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author yanjiawei
使用zookeeper需注意的几点
<ol>
<li>收到zookeeper节点数据变更事件后需要重新注册watcher，否则之后不会再收到通知</li>
<li>与zookeeper的连接断开后，在session timeout之前可以重新连接</li>
<li>节点的版本变更后，即使数据不变，也会收到通知
</ol>
 *
 *
 */
public class ZookeeperEventContainer implements Watcher {

    private static Logger log = LoggerFactory.getLogger(ZookeeperEventContainer.class);

    public static final int SESSION_TIMEOUT = 10000;

    private ZooKeeper zk;

    private CountDownLatch countDownLatch;

    private static ZookeeperEventContainer container = new ZookeeperEventContainer();

    public static ZookeeperEventContainer getInstance() {
        return container;
    }

    public ZookeeperEventContainer() {
        try {
            this.zk = createZooKeeper();
            this.monitor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ZooKeeper createZooKeeper() throws IOException, InterruptedException {
        log.debug("about to create zookeeper");
        ZooKeeper newZooKeeper = new ZooKeeper("172.16.53.137:4181", SESSION_TIMEOUT, this);
        countDownLatch = new CountDownLatch(1);
        countDownLatch.await(SESSION_TIMEOUT, TimeUnit.MILLISECONDS);
        log.debug("connection establed, session id:" + newZooKeeper.getSessionId());
        return newZooKeeper;
    }

    public void monitor() {

        //更新数据并注册watcher
        try {
            // 监听根节点数据变更、创建、删除
            this.zk.getData("/mbp/dev", true, null);
            // 监听子节点数据更新、创建、删除
            List<String> children = this.zk.getChildren("/mbp/dev", true);
            for (String child : children) {
                byte[] data = this.zk.getData("/mpb/dev/" + child, true, null);
                System.out.println("更改后的数据" + new String(data));
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public ZooKeeper getZooKeeper() {
        return zk;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zk = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {

        log.debug("received event:" + event.toString());

        switch (event.getType()) {
            case None:
                switch (event.getState()) {
                    case Expired:
                        log.debug("expired:" + this.zk.getSessionId());
                        try {
                            this.zk.close();
                            this.zk = createZooKeeper();
                            this.monitor();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case Disconnected:
                        log.debug("disconnected:" + this.zk.getSessionId());
                        try {
                            this.zk.close();
                            this.zk = createZooKeeper();
                            this.monitor();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case SyncConnected:
                        countDownLatch.countDown();
                        break;
                }
                break;
            case NodeChildrenChanged:
                //节点删除、创建同时会促发上层节点NodeChildrenChanged事件，因此可忽略
                //case NodeDeleted:
                //case NodeCreated:
            case NodeDataChanged:
                if (!this.zk.getState().isAlive()) {
                    try {
                        this.zk = createZooKeeper();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                this.monitor();
                break;
        }
    }

    public static void main(String[] args) {

        ZookeeperEventContainer container = ZookeeperEventContainer.getInstance();

        log.debug("container start");

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭连接、测试session disconnected
        try {
            ZooKeeper oldZooKeeper = new ZooKeeper("172.16.53.137:4181", 10000, null, container.getZooKeeper().getSessionId(), null);
            log.debug("close old zookeeper:" + oldZooKeeper.getSessionId());
            oldZooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true);
    }
}