package com.yeahwell.demo.thread.common.wjt04;

import java.util.concurrent.TimeUnit;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 线程中断的控制
 * 如果线程实现了复杂的算法并且分布在几个方法中，或者线程里有递归调用的方法，需要有一个更好的机制来控制线程的中断。为了达到这个目的，Java提供了 InterruptedException异常。当检査到线程中断的时候，就抛出这个异常，然后在run()中捕获并处理这个异常。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个运行对象和一个运行它的线程
        FileSearch searcher = new FileSearch("C:/", "readme.txt");
        Thread thread = new Thread(searcher);

        thread.start(); // 启动线程

        try {
            TimeUnit.SECONDS.sleep(10); // 主线程休眠10s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt(); // 中断线程
    }
}