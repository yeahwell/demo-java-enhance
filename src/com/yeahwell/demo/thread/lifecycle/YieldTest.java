package com.yeahwell.demo.thread.lifecycle;

/**
 * 通过yield方法来实现两个线程的交替执行。不过请注意：这种交替并不一定能得到保证
 * @author yeahwell
 *
 */
public class YieldTest implements Runnable {
	
	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + ": " + i);
			Thread.yield();
		}
	}

	public static void main(String[] args) {
		YieldTest runn = new YieldTest();
		Thread t1 = new Thread(runn, "FirstThread");
		Thread t2 = new Thread(runn, "SecondThread");

		t1.start();
		t2.start();

	}
}
