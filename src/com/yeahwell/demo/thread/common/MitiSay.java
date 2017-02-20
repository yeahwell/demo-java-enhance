package com.yeahwell.demo.thread.common;

public class MitiSay extends Thread {

	public MitiSay(String threadName) {
		super(threadName);
	}

	public void run() {
		System.out.println(getName() + "线程开始");
		for (int i = 0; i < 10; i++) {
			System.out.println(i + "\t" + getName());
			try {
				sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + "线程结束");
	}
}
