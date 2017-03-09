package com.yeahwell.demo.thread.common.wjt06;

import java.util.Date;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 　　在一些情形下，某个线程必须等待其它线程的终止。例如，程序在执行其他的任务时，必须先初始化一些必须的资源。可以使用线程来完成这些初始化任务，等待线程终止，再执行程序的其他任务。 
　　为了达到这个目的，使用Thread类的join()方法。当一个线程对象的join〇方法被 调用时，调用它的线程将被挂起，直到这个线程对象完成它的任务。
 * @author yeahwell
 *
 */
public class Main {
	
    public static void main(String[] args) {
        // 创建并启动数据源加载器
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader, "DataSourceThread");
        thread1.start();

        // 创建并且启动网络连接加载器
        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");
        thread2.start();

        // 等待两个线程的任务完成
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 两个任务都完成后输出一条消息
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
    
}
