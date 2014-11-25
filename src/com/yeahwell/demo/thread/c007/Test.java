package com.yeahwell.demo.thread.c007;

/**
 * Java�̣߳��̵߳ĵ���-����
 * 
 * @author leizhimin 2009-11-4 9:02:40
 */
public class Test {
	public static void main(String[] args) {
		Thread t1 = new MyThread1();
		Thread t2 = new Thread(new MyRunnable());
		t1.start();
		t2.start();
	}
}