package com.yeahwell.demo.thread.common.wjt20;

/**
 * http://blog.csdn.net/derrantcm/article/details/48152207
 * 3.2资源的并发访问控制

　　在本节中，我们将学习如何使用Java语言(Semaphore) 机制。信号量是一种计数器，用来保护一个或者多个共享资源的访问。 
如果线程要访问一个共享资源，它必须先获得信号量。如果信号量的内部计数器大于0 ,信号量将减1 ,然后允许访问这个共享资源。计数器大于0意味着有可以使用的资源, 因此线程将被允许使用其中一个资源。

　　否则，如果信号量的计数器等于0 ,信号量将会把线程置入休眠直至计数器大于0。计数器等于0的时候意味着所有的共享资源已经被其他线程使用了，所以需要访问这个共享资源的线程必须等待。

　　当线程使用完某个共享资源时，信号量必须被释放，以便其他线程能够访问共享资源。释放操作将使信号量的内部计数器增加1。 
本节中，我们将学习如何使用信号量类Semaphore来实现二进制信号量（Binary Semaphore）。二进制信号量是一种比较特殊的信号量，用来保护对唯一共享资源的访问， 因而它的内部计数器只有0和1两个值。为了演示它的使用方式，我们将实现一个打印队列，并发任务将使用它来完成打印。这个打印队列受二进制信号量保护，因而同时只有一个线程可以执行打印。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个打印队列对象
        PrintQueue printQueue = new PrintQueue();

        // 创建10个线程
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}