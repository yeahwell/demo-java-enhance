package com.yeahwell.demo.thread.common;

public class TestRunnable {

	public static void main(String[] args) {
		DoSomething ds1 = new DoSomething("跑步");
		DoSomething ds2 = new DoSomething("游泳");

		Thread t1 = new Thread(ds1);
		Thread t2 = new Thread(ds2);

		t1.start();
		t2.start();

	}
}
