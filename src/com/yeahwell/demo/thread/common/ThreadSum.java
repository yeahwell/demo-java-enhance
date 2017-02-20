package com.yeahwell.demo.thread.common;

public class ThreadSum extends Thread {
    int total = 0;

    @Override
    public void run() {

        synchronized (this) {
            for (int i = 0; i < 300000; i++) {
                total += i;
                System.out.println("------" + total);
            }
            //(完成计算了)唤醒在此对象监视器上等待的单个线程，在本例中线程ThreadInteractionTest被唤醒
            notify();
        }

    }
}
