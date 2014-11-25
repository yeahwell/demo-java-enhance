package com.yeahwell.demo.thread.c014;

/**
 * Java�̣߳�����Э��-����������ģ��
 * 
 * @author leizhimin 2009-11-4 14:54:36
 */
public class Test {
	public static void main(String[] args) {
		Godown godown = new Godown(30);
		Consumer c1 = new Consumer(50, godown);
		Consumer c2 = new Consumer(20, godown);
		Consumer c3 = new Consumer(30, godown);
		Producer p1 = new Producer(10, godown);
		Producer p2 = new Producer(10, godown);
		Producer p3 = new Producer(10, godown);
		Producer p4 = new Producer(10, godown);
		Producer p5 = new Producer(10, godown);
		Producer p6 = new Producer(10, godown);
		Producer p7 = new Producer(80, godown);

		c1.start();
		c2.start();
		c3.start();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
		p6.start();
		p7.start();
	}
}

/**
 * �ֿ�
 */
class Godown {
	public static final int max_size = 100; // �������
	public int curnum; // ��ǰ�����

	Godown() {
	}

	Godown(int curnum) {
		this.curnum = curnum;
	}

	/**
	 * ���ָ�������Ĳ�Ʒ
	 * 
	 * @param neednum
	 */
	public synchronized void produce(int neednum) {
		// �����Ƿ���Ҫ���
		while (neednum + curnum > max_size) {
			System.out.println("Ҫ���Ĳ�Ʒ����" + neednum + "����ʣ������"
					+ (max_size - curnum) + "����ʱ����ִ���������!");
			try {
				// ��ǰ������̵߳ȴ�
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// �������������������������򵥵ĸ�ĵ�ǰ�����
		curnum += neednum;
		System.out.println("�Ѿ������" + neednum + "����Ʒ���ֲִ���Ϊ" + curnum);
		// �����ڴ˶���������ϵȴ�������߳�
		notifyAll();
	}

	/**
	 * ���ָ�������Ĳ�Ʒ
	 * 
	 * @param neednum
	 */
	public synchronized void consume(int neednum) {
		// �����Ƿ�����
		while (curnum < neednum) {
			try {
				// ��ǰ������̵߳ȴ�
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// ��������������������ѣ�����򵥵ĸ�ĵ�ǰ�����
		curnum -= neednum;
		System.out.println("�Ѿ������" + neednum + "����Ʒ���ֲִ���Ϊ" + curnum);
		// �����ڴ˶���������ϵȴ�������߳�
		notifyAll();
	}
}

/**
 * �����
 */
class Producer extends Thread {
	private int neednum; // ����Ʒ������
	private Godown godown; // �ֿ�

	Producer(int neednum, Godown godown) {
		this.neednum = neednum;
		this.godown = godown;
	}

	public void run() {
		// ���ָ�������Ĳ�Ʒ
		godown.produce(neednum);
	}
}

/**
 * �����
 */
class Consumer extends Thread {
	private int neednum; // ����Ʒ������
	private Godown godown; // �ֿ�

	Consumer(int neednum, Godown godown) {
		this.neednum = neednum;
		this.godown = godown;
	}

	public void run() {
		// ���ָ�������Ĳ�Ʒ
		godown.consume(neednum);
	}
}