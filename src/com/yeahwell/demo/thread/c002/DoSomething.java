package com.yeahwell.demo.thread.c002;

public class DoSomething implements Runnable {

	private String name;

	public DoSomething(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			for (long k = 0; k < 100000000; k++);  //�ó���ȴ�
			System.out.println(name + ": " + i);
		}
	}

}
