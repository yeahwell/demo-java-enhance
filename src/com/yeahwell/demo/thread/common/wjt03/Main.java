package com.yeahwell.demo.thread.common.wjt03;

import java.util.concurrent.TimeUnit;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {

        Thread task = new PrimeGenerator();
        task.start(); // 启动质数生成线程
        try {
            TimeUnit.SECONDS.sleep(5); // 主线程休眠5s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.interrupt(); // 质数生成线程中断
    }
}