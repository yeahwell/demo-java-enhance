package com.yeahwell.demo.thread.c008;

public class MyRunnable implements Runnable {
    public void run() {
            for (int i = 0; i < 10; i++) {
                    System.out.println("�߳�2��" + i + "��ִ�У�");
                    try {
                            Thread.sleep(100);
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
            }
    }
}