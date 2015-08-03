package com.yeahwell.demo.zookeeper.counter;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import com.google.common.collect.Lists;

/**
 * 计数器是用来计数的, 利用ZooKeeper可以实现一个集群共享的计数器。 
 * 只要使用相同的path就可以得到最新的计数器值， 这是由ZooKeeper的一致性保证的。Curator有两个计数器， 一个是用int来计数，一个用long来计数。
 * @author yanjiawei
 *
 */
public class SharedCounterExample implements SharedCountListener{
    private static final int QTY = 5;
    private static final String PATH = "/examples/counter";

    public static void main(String[] args) throws IOException, Exception {
        final Random rand = new Random();
        SharedCounterExample example = new SharedCounterExample();
        
        	TestingServer server = new TestingServer();
            CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000, 3));
            client.start();

            /**
             * SharedCount类使用int类型来计数
             * SharedCount代表计数器， 可以为它增加一个SharedCountListener，当计数器改变时此Listener可以监听到改变的事件，
             * 而SharedCountReader可以读取到最新的值， 包括字面值和带版本信息的值VersionedValue。
             */
            SharedCount baseCount = new SharedCount(client, PATH, 0);
            baseCount.addListener(example);
            //计数器必须start,使用完之后必须调用close关闭它
            baseCount.start();

            List<SharedCount> examples = Lists.newArrayList();
            //使用5个线程为计数值增加一个10以内的随机数
            ExecutorService service = Executors.newFixedThreadPool(QTY);
            for (int i = 0; i < QTY; ++i) {
                final SharedCount count = new SharedCount(client, PATH, 0);
                examples.add(count);
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        count.start();
                        Thread.sleep(rand.nextInt(10000));
                        // trySetCount去设置计数器。第一个参数提供当前的VersionedValue,如果期间其它client更新了此计数值， 你的更新可能不成功，但是这时你的client更新了最新的值，所以失败了你可以尝试再更新一次。
                        //而setCount是强制更新计数器的值。
                        System.out.println("Increment:" + count.trySetCount(count.getVersionedValue(), count.getCount() + rand.nextInt(10)));
                        return null;
                    }
                };
                service.submit(task);
            }

            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);

            for (int i = 0; i < QTY; ++i) {
                examples.get(i).close();
            }
            baseCount.close();


    }

    @Override
    public void stateChanged(CuratorFramework arg0, ConnectionState arg1) {
        System.out.println("State changed: " + arg1.toString());
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
        System.out.println("Counter's value is changed to " + newCount);        
    }

}