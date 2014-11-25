package com.yeahwell.demo.thread.c006;

/**
 * �����߳�
 * 
 * @author leizhimin 2008-9-20 11:15:46
 */
public class Calculator extends Thread {
	int total;

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 101; i++) {
				total += i;
			}
		}
		// ֪ͨ�����ڴ˶����ϵȴ���߳�
		notifyAll();
	}
}
