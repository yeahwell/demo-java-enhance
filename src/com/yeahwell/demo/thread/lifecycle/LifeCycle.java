package com.yeahwell.demo.thread.lifecycle;

public class LifeCycle extends Thread {
    public void run() {
        int n = 0;
        while ((++n) < 1000)
            ;
    }

    public static void main(String[] args) throws Exception {
        LifeCycle thread = new LifeCycle();
        System.out.println("isAlive: " + thread.isAlive());
        thread.start();
        System.out.println("isAlive: " + thread.isAlive());
        thread.join(); // 等线程thread结束后再继续执行
        System.out.println("thread已经结束!");
        System.out.println("isAlive: " + thread.isAlive());
    }
}
