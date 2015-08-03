package com.yeahwell.demo.zookeeper.leader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;
import com.google.common.collect.Lists;

/**
在分布式计算中， leader election是很重要的一个功能， 
这个选举过程是这样子的： 指派一个进程作为组织者，将任务分发给各节点。
在任务开始前，哪个节点都不知道谁是leader或者coordinator. 
当选举算法开始执行后，每个节点最终会得到一个唯一的节点作为任务leader.
除此之外， 选举还经常会发生在leader意外宕机的情况下，新的leader要被选举出来。 
 * @author yanjiawei
 *
 */
public class LeaderLatchExample {

	private static final int CLIENT_QTY = 10;
	private static final String PATH = "/examples/leader";

	public static void main(String[] args) throws Exception {
		List<CuratorFramework> clients = Lists.newArrayList();
		List<LeaderLatch> examples = Lists.newArrayList();
		TestingServer server = new TestingServer();
		try {
			//创建了10个LeaderLatch，启动后它们中的一个会被选举为leader。 
			for (int i = 0; i < CLIENT_QTY; ++i) {
				CuratorFramework client = CuratorFrameworkFactory.newClient(
						server.getConnectString(), new ExponentialBackoffRetry(
								1000, 3));
				clients.add(client);
				LeaderLatch example = new LeaderLatch(client, PATH, "Client #"
						+ i);
				examples.add(example);
				client.start();
				//因为选举会花费一些时间，start后并不能马上就得到leader。
				example.start();
			}
			Thread.sleep(20000);
			LeaderLatch currentLeader = null;
			for (int i = 0; i < CLIENT_QTY; ++i) {
				LeaderLatch example = examples.get(i);
				//查看一个给定的实例是否是leader
				if (example.hasLeadership())
					currentLeader = example;
			}
			System.out.println("current leader is " + currentLeader.getId());
			System.out.println("release the leader " + currentLeader.getId());
			currentLeader.close();
			examples.get(0).await(2, TimeUnit.SECONDS);
			System.out.println("Client #0 maybe is elected as the leader or not although it want to be");
			System.out.println("the new leader is " + examples.get(0).getLeader().getId());
			System.out.println("Press enter/return to quit\n");
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Shutting down...");
			for (LeaderLatch exampleClient : examples) {
				CloseableUtils.closeQuietly(exampleClient);
			}
			for (CuratorFramework client : clients) {
				CloseableUtils.closeQuietly(client);
			}
			CloseableUtils.closeQuietly(server);
		}
	}
}
