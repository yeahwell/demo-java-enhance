package com.yeahwell.demo.thread.c006;

public class ThreadA {

	public static void main(String[] args) {
		ThreadB b = new ThreadB();
		// ���������߳�
		b.start();
		// �߳�Aӵ��b�����ϵ����߳�Ϊ�˵���wait()��notify()���������̱߳������Ǹ��������ӵ����
		synchronized (b) {
			try {
				System.out.println("�ȴ����b��ɼ��㡣����");
				// ��ǰ�߳�A�ȴ�
				b.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("b���������ܺ��ǣ�" + b.total);
		}
	}
}
