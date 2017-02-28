package com.yeahwell.demo.thread.syn;

/**
 * 
Java语言包含两种内在的同步机制：同步块（或方法）和 volatile 变量。这两种机制的提出都是为了实现代码线程的安全性。
其中 Volatile 变量的同步性较差（但有时它更简单并且开销更低），而且其使用也更容易出错。

public volatile boolean exit = false;
在定义exit时，使用了一个Java关键字volatile，这个关键字的目的是使exit同步，也就是说在同一时刻只能由一个线程来修改exit的值.

声明为volatile的简单变量如果当前值由该变量以前的值相关，那么volatile关键字不起作用，也就是说如下的表达式都不是原子操作：
n = n + 1;
n++;
如果要想使这种情况变成原子操作，需要使用synchronized关键字
 */
public class ThreadVolatile extends Thread {
    public static volatile int n = 0;

    public void run() {
        for (int i = 0; i < 10; i++)
            try {
                n = n + 1;
                sleep(3); // 为了使运行结果更随机，延迟3毫秒

            } catch (Exception e) {
            }
    }

    public static void main(String[] args) throws Exception {

        Thread threads[] = new Thread[100];
        for (int i = 0; i < threads.length; i++)
            // 建立100个线程
            threads[i] = new ThreadVolatile();
        for (int i = 0; i < threads.length; i++)
            // 运行刚才建立的100个线程
            threads[i].start();
        for (int i = 0; i < threads.length; i++)
            // 100个线程都执行完后继续
            threads[i].join();
        System.out.println("n=" + ThreadVolatile.n);
    }
}
