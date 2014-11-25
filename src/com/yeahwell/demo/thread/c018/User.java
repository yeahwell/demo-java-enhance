package com.yeahwell.demo.thread.c018;

import java.util.concurrent.locks.Lock;

/**
 * ���ÿ����û�
 */
public class User implements Runnable {
	
	private String name; // �û���
	private MyCount myCount; // ��Ҫ�������˻�
	private int iocash; // �����Ľ���Ȼ����֮����
	private Lock myLock; // ִ�в�������������

	User(String name, MyCount myCount, int iocash, Lock myLock) {
		this.name = name;
		this.myCount = myCount;
		this.iocash = iocash;
		this.myLock = myLock;
	}

	public void run() {
		// ��ȡ��
		myLock.lock();
		// ִ���ֽ�ҵ��
		System.out.println(name + "���ڲ���" + myCount + "�˻������Ϊ" + iocash
				+ "����ǰ���Ϊ" + myCount.getCash());
		myCount.setCash(myCount.getCash() + iocash);
		System.out.println(name + "����" + myCount + "�˻��ɹ������Ϊ" + iocash
				+ "����ǰ���Ϊ" + myCount.getCash());
		// �ͷ���������߳�û�л��ִ����
		myLock.unlock();
	}
}