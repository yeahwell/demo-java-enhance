package com.yeahwell.demo.thread.common.wjt07;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * Java里有一种特殊的线程叫做守护（Daemon)线程。这种线程的优先级很低，通常来说，当同一个应用程序里没有其他的线程运行的时候，守护线程才运行。当守护线程是程序中唯一运行的线程时，守护线程执行结束后，JVM也就结束了这个程序。 
	因为这种特性，守护线程通常被用来做为同一程序中普通线程（也称为用户线程）的 服务提供者。它们通常是无限循环的，以等待服务请求或者执行线程的任务。它们不能做重要的工作，因为我们不可能知道守护线程什么时候能够获取CPU时钟，并且，在没有其他线程运行的时候，守护线程随时可能结束。一个典型的守护线程是Java的垃圾回收器 (Garbage Collector)。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个用于存放事件对象的队列
        Deque<Event> deque = new ArrayDeque<Event>();

        // 创建一个写任务的对象，并且创建三个线程去调用这个对象
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }

        // 创建一个事件清除任务，并且启动这个任务
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }
}