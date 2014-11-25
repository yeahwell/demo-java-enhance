package com.yeahwell.demo.thread.c008;

public class MyThread1 extends Thread {
    public void run() {
            for (int i = 0; i < 10; i++) {
                    System.out.println("�߳�1��" + i + "��ִ�У�");
                    try {
                            Thread.sleep(100);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
            }
    }
} 