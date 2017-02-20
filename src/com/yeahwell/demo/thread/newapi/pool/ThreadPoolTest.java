package com.yeahwell.demo.thread.newapi.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池用法
 * 
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolTest test=new ThreadPoolTest();
        
        //创建一个可重用固定线程数的线程池 
        ExecutorService pool = Executors.newFixedThreadPool(2); 
        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口 
        Thread t1 = test.new MyThread(); 
        Thread t2 = test.new MyThread(); 
        Thread t3 = test.new MyThread(); 
        Thread t4 = test.new MyThread(); 
        Thread t5 = test.new MyThread(); 
        //将线程放入池中进行执行 
        pool.execute(t1); 
        pool.execute(t2); 
        pool.execute(t3); 
        pool.execute(t4); 
        pool.execute(t5); 
        //关闭线程池 
        pool.shutdown(); 
    }
    
    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在执行。");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
