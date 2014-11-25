package com.yeahwell.demo.thread.c001;

public class TestMitiThread {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "�߳̿�ʼ����");
		new MitiSay("A").start();
		new MitiSay("B").start();
		System.out.println(Thread.currentThread().getName() + "�߳̽�������");
		
	}
}
