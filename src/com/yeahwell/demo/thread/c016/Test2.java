package com.yeahwell.demo.thread.c016;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
* Java�̣߳��̳߳�-
*
* @author Administrator 2009-11-4 23:30:44
*/
public class Test2 {
        public static void main(String[] args) {
                //����һ���̳߳أ���ɰ����ڸ��ӳٺ�����������߶��ڵ�ִ�С�
                ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
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
                //ʹ���ӳ�ִ�з��ķ���
                pool.schedule(t4, 10, TimeUnit.MILLISECONDS);
                pool.schedule(t5, 10, TimeUnit.MILLISECONDS);
                //�ر��̳߳�
                pool.shutdown();
        }
} 