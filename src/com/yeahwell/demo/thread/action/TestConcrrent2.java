package com.yeahwell.demo.thread.action;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TestConcrrent2 {

	final static int MAX_QPS = 10;
	final static int MAX_TASK_NUM = 100;
	final static int MAX_THREAD_NUM = 100;

	final static Semaphore semaphore = new Semaphore(MAX_QPS);	
	final static CountDownLatch overLatch = new CountDownLatch(MAX_TASK_NUM);

	public static void main(String... args) throws Exception {
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				semaphore.release(MAX_QPS / 2);
			}
		}, 1000, 500, TimeUnit.MILLISECONDS);

		// lots of concurrent calls:100 * 1000
		ExecutorService pool = Executors.newFixedThreadPool(MAX_THREAD_NUM);
		for (int i = MAX_TASK_NUM; i > 0; i--) {
			final int x = i;
			pool.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 5; j > 0; j--) {
						semaphore.acquireUninterruptibly(1);
						remoteCall(x, j);
					}
					overLatch.countDown();
				}
			});
		}
		pool.shutdown();		
//		pool.awaitTermination(1, TimeUnit.HOURS);
		overLatch.await();
		System.out.println("DONE");

	}

	private static void remoteCall(int i, int j) {
		System.out.println(String.format("%s - %s: %d %d", new Date(),Thread.currentThread(), i, j));
	}

}
