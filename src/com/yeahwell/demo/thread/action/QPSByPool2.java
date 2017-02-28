package com.yeahwell.demo.thread.action;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class QPSByPool2 {

	private static int MAX_EXE_COUNT = 10;

	private static AtomicInteger count = new AtomicInteger(MAX_EXE_COUNT);

	public static void main(String[] args) throws InterruptedException,
			IOException {
		
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println("1秒过去了");
					count.set(MAX_EXE_COUNT);
				}
			}
		}.start();
		
		Executor e = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 100; i++) {
			e.execute(new Runnable() {
				public void run() {
					while (count.getAndDecrement() <= 0) {
					}
					System.out.println(Thread.currentThread().getName() + "我调用了一次");
				}
			});
		}
	}

}
