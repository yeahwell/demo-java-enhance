package com.yeahwell.demo.thread.common.wjt10;


import java.util.concurrent.TimeUnit;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 1.11线程的分组

　　Java并发API提供了一个功能，它能够把线程分组。这允许我们把一个组的线程当成一个单一的单元，对组内线程对象进行访问并操作它们。例如，对于一些执行同样任务的线程，你想控制它们，不管多少线程在运行，只需要一个单一的调用，所有这些线 程的运行都会被中断. 
　　Java提供ThreadGroup类表示一组线程。线程组可以包含线程对象，也可以包含其他的线程组对象，它是一个树形结构。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {

        // 创建一个线程组
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        // 创建一个结果对象
        Result result = new Result();

        // 创建一个搜索任务，并且创建5个线程去运行这个任务
        SearchTask searchTask = new SearchTask(result);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(threadGroup, searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // 输出线程组的信息
        System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list(); // 将有关此线程组的信息打印到标准输出。

        Thread[] threads = new Thread[threadGroup.activeCount()]; // 返回此线程组中活动线程的估计数。
        threadGroup.enumerate(threads); // 把此线程组及其子组中的所有活动线程复制到指定数组中。
        for (int i = 0; i < threadGroup.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
        }

        // 等待线程结束
        waitFinish(threadGroup);

        // 中断线程组中的所有线程
        threadGroup.interrupt();

    }

    /**
     * 等待线程组中的一个线程结束
     *
     * @param threadGroup 线程组
     */
    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount() > 9) { // 如果线程组中的活动线程数大于9个，当前调用线程就休眠1秒，直到线程数小于9个
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
