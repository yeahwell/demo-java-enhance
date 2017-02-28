package com.yeahwell.demo.thread.action;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个流控程序。
 * 控制客户端每秒调用某个远程服务不超过N次，客户端是会多线程并发调用，需要一个轻量简洁的实现，
 * 大家看看下面的一个实现，然后可以自己写一个实现。
 * @author yeahwell
 *
 */
public class QPSByPool {

	final static int MAX_QPS = 10;

	final static Semaphore semaphore = new Semaphore(MAX_QPS);

	public static void main(String... args) throws Exception {

		//每500毫秒，放进来MAX_QPS/2的请求
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				semaphore.release(MAX_QPS / 2);
			}
		}, 1000, 500, TimeUnit.MILLISECONDS);

		// lots of concurrent calls:100 * 1000
		ExecutorService pool = Executors.newFixedThreadPool(100);
		for (int i = 100; i > 0; i--) {
			final int x = i;
			pool.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 1000; j > 0; j--) {
						semaphore.acquireUninterruptibly(1);
						remoteCall(x, j);
					}
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.HOURS);

		System.out.println("DONE");
	}

	private static void remoteCall(int i, int j) {
		System.out.println(String.format("%s - %s: %d %d", new Date(),
				Thread.currentThread(), i, j));
	}

}