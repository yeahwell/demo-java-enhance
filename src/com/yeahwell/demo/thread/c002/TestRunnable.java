package com.yeahwell.demo.thread.c002;

public class TestRunnable {

	public static void main(String[] args) {
		DoSomething ds1 = new DoSomething("����");
		DoSomething ds2 = new DoSomething("����");

		Thread t1 = new Thread(ds1);
		Thread t2 = new Thread(ds2);

		t1.start();
		t2.start();

	}
}
