package com.yeahwell.demo.thread.newapi;

import java.util.concurrent.CountDownLatch;

public class Countdownlatch {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(0);
        try {
            // 立刻返回
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test");
    }
}