package com.yeahwell.demo.thread.lifecycle;

/**
 * 通过这个例子，大家应该很清楚，wait方法的使用必须在同步的范围内，否则就会抛出IllegalMonitorStateException异常，
 * wait方法的作用就是阻塞当前线程等待notify/notifyAll方法的唤醒，或等待超时后自动唤醒。
 * @author yeahwell
 *
 */
public class WaitTest2 {

    public synchronized void testWait(){//增加Synchronized关键字
        System.out.println("Start-----");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End-------");
    }

    public static void main(String[] args) {
        final WaitTest2 test = new WaitTest2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.testWait();
            }
        }).start();
    }
}
