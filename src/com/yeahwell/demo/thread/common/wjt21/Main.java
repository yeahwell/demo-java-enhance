package com.yeahwell.demo.thread.common.wjt21;

/**
 * http://blog.csdn.net/derrantcm/article/details/48152207
 * 3.3资源的多副本的并发访问控制
　　信号量也可以用来保护一个资源的多个副本，或者被多个线程同时执行的临界区。
　　在本节中，我们将学习如何使用信号量来保护一个资源的多个副本。我们将实现这样 的范例：一个打印队列，它将被三个不同的打印机使用。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个打印对队对象
        PrintQueue printQueue = new PrintQueue();

        // 创建12个线程，运行作业任务，这些任务都向同一个打印队列对象发出打印请求
        Thread thread[] = new Thread[12];
        for (int i = 0; i < 12; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        // 启动12个线程
        for (int i = 0; i < 12; i++) {
            thread[i].start();
        }
    }
}