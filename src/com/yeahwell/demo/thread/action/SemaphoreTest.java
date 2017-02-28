package com.yeahwell.demo.thread.action;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * TODO wenxing 类描述.
 *
 * @version : Ver 1.0
 * @author : wenxing
 * @date : 2015年8月18日 上午10:54:09
 */
public class SemaphoreTest {

	/**
	 * 每秒QPS限制
	 */
	final static int MAX_QPS = 10;
	/**
	 * 调用计数
	 */
	final static Semaphore CALL_SIZE = new Semaphore(MAX_QPS);

	public static void main(String[] args) throws InterruptedException {

		// 如果是限制集群中的方法每秒调用次数，这种每秒定时重置的方法就需要独立到单独的应用中去，或者采用redis的过期机制
		ScheduledExecutorService scheduledService = Executors
				.newScheduledThreadPool(1);
		scheduledService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// 释放最多MAX_QPS个许可
				CALL_SIZE.release(MAX_QPS - CALL_SIZE.availablePermits());
			}
		}, 1000, 1000, TimeUnit.MILLISECONDS);// 执行间隔为1秒，初次延时1秒执行

		// 模拟每1秒调用100次,连续模拟5次，但是只有10次可以正确调用，其它的等待下一秒的许可发放
		ExecutorService pool = Executors.newFixedThreadPool(100);
		for (int i = 100; i > 0; i--) {
			final int x = i;
			pool.submit(new Runnable() {
				@Override
				public void run() {
					// 调用业务方法
					try {
						// 获取许可，最久等待2秒，可以避免等待线程过多
						// CALL_SIZE.tryAcquire(2000, TimeUnit.MILLISECONDS);
						for (int j = 1000; j > 0; j--) {
							CALL_SIZE.acquire();
							remoteCall(x, j);
						}
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}

				}

			});

		}
		// 休息一秒
		TimeUnit.MILLISECONDS.sleep(1000);
		pool.shutdown();
		// scheduledService.shutdown();
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

}