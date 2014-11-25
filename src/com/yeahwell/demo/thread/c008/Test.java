package com.yeahwell.demo.thread.c008;

/**
 * Java�̣߳��̵߳ĵ���-���ȼ�
 * 
 * @author leizhimin 2009-11-4 9:02:40
 */
public class Test {
	public static void main(String[] args) {
		Thread t1 = new MyThread1();
		Thread t2 = new Thread(new MyRunnable());
		t1.setPriority(10);
		t2.setPriority(1);

		t2.start();  //t2�ȿ�ʼ
		t1.start();
	}
}