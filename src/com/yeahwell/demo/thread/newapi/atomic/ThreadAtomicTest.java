package com.yeahwell.demo.thread.newapi.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程-原子量
 * 
 */
public class ThreadAtomicTest {
    public static void main(String[] args) {
    	
    	
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Runnable t1 = new AtomicRunnable("张三", 2000);
        Runnable t2 = new AtomicRunnable("李四", 3600);
        Runnable t3 = new AtomicRunnable("王五", 2700);
        Runnable t4 = new AtomicRunnable("老张", 600);
        Runnable t5 = new AtomicRunnable("老牛", 1300);
        Runnable t6 = new AtomicRunnable("胖子", 800);
        // 执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        
        // 关闭线程池
        //shutdown并不是直接关闭线程池，而是不再接受新的任务…如果线程池内有任务，那么把这些任务执行完毕后，关闭线程池
        pool.shutdown();
        
        //这个方法表示不再接受新的任务，并把任务队列中的任务直接移出掉，如果有正在执行的，尝试进行停止
//        pool.shutdownNow();
        
        //原子量虽然可以保证单个变量在某一个操作过程的安全，但无法保证你整个代码块，或者整个程序的安全性。因此，通常还应该使用锁等同步机制来控制整个程序的安全性。

    }

     
}