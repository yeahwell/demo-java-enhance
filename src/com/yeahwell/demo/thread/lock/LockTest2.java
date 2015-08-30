package com.yeahwell.demo.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用Lock替代synchronized
 *
 *
 */
public class LockTest2 {
	
	public static void main(String[] args) {
		new LockTest2().init();
	}

	private void init() {
		final Outputer outputer = new Outputer();
		// A线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("zhangsan");
				}
			}
		}).start();
		// B线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("lisi");
				}
			}
		}).start();
	}

	static class Outputer {
		Lock lock = new ReentrantLock();

		/**
		 * 打印字符
		 *
		 * @param name
		 */
		public void output(String name) {
			int len = name.length();
			lock.lock();
			try {
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();
			}
		}
	}
}