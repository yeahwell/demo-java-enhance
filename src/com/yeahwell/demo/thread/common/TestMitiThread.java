package com.yeahwell.demo.thread.common;

public class TestMitiThread {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "线程开始");
		new MitiSay("A").start();
		new MitiSay("B").start();
		System.out.println(Thread.currentThread().getName() + "线程结束");
		
	}
}
