package com.yeahwell.demo.thread.c007;

public class MyRunnable implements Runnable {
	
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("�߳�2��" + i + "��ִ�У�");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}