package com.yeahwell.demo.thread.common;

public class ThreadSum2 extends Thread {
    int total = 0;

    @Override
    public void run() {

        synchronized (this) {
            for (int i = 0; i < 101; i++) {
                total += i;
            }
            //通知所有在此对象上等待的线程 
            notifyAll();
        }

    }
}
