package com.yeahwell.demo.thread.c016;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* Java�̣߳��̳߳�-
*
*/ 
public class Test {
	
	public static void main(String[] args) {
		//����һ�������ù̶��߳�����̳߳� 
//		ExecutorService pool = Executors.newFixedThreadPool(2);
		
        //����һ��ʹ�õ��� worker �̵߳� Executor�����޽���з�ʽ�����и��̡߳�
//        ExecutorService pool = Executors.newSingleThreadExecutor(); 
		
        //����һ���ɸ����Ҫ�������̵߳��̳߳أ���������ǰ������߳̿���ʱ,���������ǡ�
        ExecutorService pool = Executors.newCachedThreadPool(); 
        
        //����ʵ����Runnable�ӿڶ���Thread����ȻҲʵ����Runnable�ӿ�
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread(); 
        //���̷߳�����н���ִ��
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5); 
        //�ر��̳߳�
        pool.shutdown(); 
	}

}
