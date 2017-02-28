package com.yeahwell.demo.thread.action;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现一个流控程序。控制客户端每秒调用某个远程服务不超过N次，客户端是会多线程并发调用
 *
 * @version : Ver 1.0
 * @author : wenxing
 * @date : 2015年8月18日 上午10:31:47
 */
public class RestrictedCallTest {
	/**
	 * 每秒QPS限制
	 */
	final static int MAX_QPS = 10;
	/**
	 * 调用计数
	 */
	final static AtomicInteger CALL_SIZE = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {

		// 如果是限制集群中的方法每秒调用次数，这种每秒定时重置的方法就需要独立到单独的应用中去，或者采用redis的过期机制
		ScheduledExecutorService scheduledService = Executors
				.newScheduledThreadPool(1);
		scheduledService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				CALL_SIZE.getAndSet(0);
			}

		}, 1000, 1000, TimeUnit.MILLISECONDS);

		// 模拟每1秒调用100次,连续模拟5次，但是只有10次可以正确调用，其它的会被拒绝
		ExecutorService pool = Executors.newFixedThreadPool(100);
		for (int i = 100; i > 0; i--) {
			final int x = i;
			pool.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 1000; j > 0; j--) {
						// 判断每秒的调用次数,在实际业务当中，不外就是一个拦截来实现
						if (getCallSize() >= MAX_QPS) {
							System.out.println(String.format(
									"每秒最多调用%s次该方法，第%d轮拒绝", MAX_QPS, j));
						} else {
							incCall();
							// 调用业务方法
							remoteCall(x, j);

						}
					}
				}

			});
		}
		// 休息一秒
		TimeUnit.MILLISECONDS.sleep(10);
		pool.shutdown();
		scheduledService.shutdown();
	}

	/**
	 * 业务方法
	 *
	 * @author : wenxing 2015年8月18日 上午10:28:39
	 * @param i
	 * @param j
	 */
	private static void remoteCall(int i, int j) {
		System.out.println(String.format("%s – %s: 第%d轮调用,第%d次执行", new Date(),
				Thread.currentThread(), j, i));
	}

	/**
	 * 增加方法调用次数
	 * 如果是限制集群中的方法每秒调用次数，可以采用redis进行集中存储,不过采用redis集中存储，限制的调用次数，可能会超出调用次数最大限制
	 *
	 * @author : wenxing 2015年8月18日 上午10:28:39
	 * @return 返回最新的调用次数
	 */
	private static int incCall() {
		return CALL_SIZE.incrementAndGet();
	}

	/**
	 * 获取方法的调用次数 如果是限制集群中的方法每秒调用次数，可以采用redis进行集中存储
	 *
	 * @author :wenxing 2015年8月18日 上午10:30:01
	 * @return 返回当前的调用次数
	 */
	private static int getCallSize() {
		return CALL_SIZE.get();
	}
}