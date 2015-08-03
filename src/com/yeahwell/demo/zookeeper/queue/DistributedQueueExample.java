package com.yeahwell.demo.zookeeper.queue;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.queue.DistributedQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

/**
 * 
创建队列使用QueueBuilder,它也是其它队列的创建类
QueueSerializer提供了对队列中的对象的序列化和反序列化。
QueueConsumer是消费者， 它可以接收队列的数据。 处理队列中的数据的代码逻辑可以放在QueueConsumer.consumeMessage()中
 * @author yanjiawei
 *
 */
public class DistributedQueueExample {
	
	private static final String PATH = "/example/queue";

	public static void main(String[] args) throws Exception {
		TestingServer server = new TestingServer();
		CuratorFramework client = null;
		DistributedQueue<String> queue = null;
		try {
			client = CuratorFrameworkFactory.newClient(server
					.getConnectString(), new ExponentialBackoffRetry(1000, 3));
			client.getCuratorListenable().addListener(new CuratorListener() {
				@Override
				public void eventReceived(CuratorFramework client,
						CuratorEvent event) throws Exception {
					System.out.println("CuratorEvent: "
							+ event.getType().name());
				}
			});
			client.start();
			QueueConsumer<String> consumer = createQueueConsumer();
			QueueBuilder<String> builder = QueueBuilder.builder(client,
					consumer, createQueueSerializer(), PATH);
			queue = builder.buildQueue();
			queue.start();

			for (int i = 0; i < 10; i++) {
				queue.put(" test-" + i);
				Thread.sleep((long) (3 * Math.random()));
			}

			Thread.sleep(20000);

		} catch (Exception ex) {
		} finally {
			CloseableUtils.closeQuietly(queue);
			CloseableUtils.closeQuietly(client);
			CloseableUtils.closeQuietly(server);
		}
	}

	private static QueueSerializer<String> createQueueSerializer() {
		return new QueueSerializer<String>() {
			@Override
			public byte[] serialize(String item) {
				return item.getBytes();
			}

			@Override
			public String deserialize(byte[] bytes) {
				return new String(bytes);
			}

		};
	}

	private static QueueConsumer<String> createQueueConsumer() {
		return new QueueConsumer<String>() {
			@Override
			public void stateChanged(CuratorFramework client,
					ConnectionState newState) {
				System.out.println("connection new state: " + newState.name());
			}

			@Override
			public void consumeMessage(String message) throws Exception {
				System.out.println("consume one message: " + message);
			}

		};
	}
}