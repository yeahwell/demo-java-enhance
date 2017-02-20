package com.yeahwell.demo.thread.newapi.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程-原子量
 * 
 */
public class ThreadAtomicTest2 {
	
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Lock lock = new ReentrantLock(false);
        Runnable t1 = new AtomicRunnable2("张三", 2000, lock);
        Runnable t2 = new AtomicRunnable2("李四", 1000, lock);
        Runnable t3 = new AtomicRunnable2("王五", 2000, lock);
        Runnable t4 = new AtomicRunnable2("老张", 3000, lock);
        Runnable t5 = new AtomicRunnable2("老牛", 4000, lock);
        Runnable t6 = new AtomicRunnable2("胖子", 5000, lock);
        // 执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        // 关闭线程池
        pool.shutdown();
    }

}
