package com.yeahwell.demo.thread.c013;

/**
 * Java�̣߳��̵߳�ͬ��-ͬ�������
 * 
 * @author leizhimin 2009-11-4 11:23:32
 */
public class Test {
	public static void main(String[] args) {
		User u = new User("����", 100);
		MyThread t1 = new MyThread("�߳�A", u, 20);
		MyThread t2 = new MyThread("�߳�B", u, -60);
		MyThread t3 = new MyThread("�߳�C", u, -80);
		MyThread t4 = new MyThread("�߳�D", u, -30);
		MyThread t5 = new MyThread("�߳�E", u, 32);
		MyThread t6 = new MyThread("�߳�F", u, 21);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
}

class MyThread extends Thread {
	private User u;
	private int y = 0;

	MyThread(String name, User u, int y) {
		super(name);
		this.u = u;
		this.y = y;
	}

	public void run() {
		u.oper(y);
	}
}

class User {
	private String code;
	private int cash;

	User(String code, int cash) {
		this.code = code;
		this.cash = cash;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * ҵ�񷽷�
	 * 
	 * @param x
	 *            ���x��Ԫ
	 */
	public void oper(int x) {
		try {
			Thread.sleep(10L);
			synchronized (this) {
				this.cash += x;
				System.out.println(Thread.currentThread().getName()
						+ "���н������ӡ�" + x + "������ǰ�û��˻����Ϊ��" + cash);
			}
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "User{" + "code='" + code + '\'' + ", cash=" + cash + '}';
	}
}