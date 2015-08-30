package com.yeahwell.demo.thread.newapi;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicRunnable implements Runnable {
    private static AtomicLong aLong = new AtomicLong(10000); // 原子量，每个线程都可以自由操作
    private String name; // 操作人
    private int x; // 操作数额

    AtomicRunnable(String name, int x) {
        this.name = name;
        this.x = x;
    }

    public void run() {
        System.out.println(name + "执行了" + x + "，当前余额：" + aLong.addAndGet(x));
    }

}