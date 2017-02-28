package com.yeahwell.demo.thread.lifecycle;

/**
 * 不使用join方法
 * @author yeahwell
 *
 */
public class JoinTest implements Runnable {
	
	@Override
	public void run() {
		try {
			System.out
					.println(Thread.currentThread().getName() + " start-----");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " end------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Thread test = new Thread(new JoinTest());
			test.start();
		}

		System.out.println("Finished~~~");
	}
}