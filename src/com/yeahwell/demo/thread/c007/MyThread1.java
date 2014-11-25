package com.yeahwell.demo.thread.c007;

public class MyThread1 extends Thread {
	
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("�߳�1��" + i + "��ִ�У�");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
