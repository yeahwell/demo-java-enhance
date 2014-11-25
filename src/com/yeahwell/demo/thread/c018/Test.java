package com.yeahwell.demo.thread.c018;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java�̣߳���
 * 
 * @author leizhimin 2009-11-5 10:57:29
 */
public class Test {
	public static void main(String[] args) {
		// �����������ʵ��˻�
		MyCount myCount = new MyCount("95599200901215522", 10000);
		// ����һ�������
		Lock lock = new ReentrantLock();
		// ����һ���̳߳�
		ExecutorService pool = Executors.newCachedThreadPool();
		// ����һЩ���������û���һ�����ÿ�����Ĵ棬ȡ��ȡ�������ְ�
		User u1 = new User("����", myCount, -4000, lock);
		User u2 = new User("�������", myCount, 6000, lock);
		User u3 = new User("�������", myCount, -8000, lock);
		User u4 = new User("����", myCount, 800, lock);
		// ���̳߳���ִ�и����û��Ĳ���
		pool.execute(u1);
		pool.execute(u2);
		pool.execute(u3);
		pool.execute(u4);
		// �ر��̳߳�
		pool.shutdown();
	}
}