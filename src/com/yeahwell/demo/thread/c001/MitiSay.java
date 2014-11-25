package com.yeahwell.demo.thread.c001;

public class MitiSay extends Thread {

	public MitiSay(String threadName) {
		super(threadName);
	}

	public void run() {
		System.out.println(getName() + "�߳̿�ʼ����");
		for (int i = 0; i < 10; i++) {
			System.out.println(i + "\t" + getName());
			try {
				sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + "���н���");
	}
}
