package com.yeahwell.demo.thread.lifecycle;

/**
 * 从结果可以看出：调用notify方法时只有线程Thread-0被唤醒，但是调用notifyAll时，所有的线程都被唤醒了。

 * @author yeahwell
 *
 */
public class NotifyTest {
	
	public synchronized void testWait() {
		System.out.println(Thread.currentThread().getName() + " Start-----");
		try {
			wait(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " End-------");
	}

	public static void main(String[] args) throws InterruptedException {
		final NotifyTest test = new NotifyTest();
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					test.testWait();
				}
			}).start();
		}

		synchronized (test) {
			test.notify();
		}
		Thread.sleep(3000);
		System.out.println("-----------分割线-------------");

		synchronized (test) {
			test.notifyAll();
		}
	}
}
