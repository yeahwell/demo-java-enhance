package com.yeahwell.demo.thread.newapi.pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * http://blog.csdn.net/truelove12358/article/details/53484705
 * @author yeahwell
 *
 */
public class ScheduledThreadPoolExecutorDemo {  
    
    public static void main(String[] args) {  
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(1);  
        /** 
         * new timeTaskForException() 要执行的任务线程 
         * 1000：延迟多长时间执行 
         * 2000: 每隔多少长时间执行一次 
         * TimeUnit.MILLISECONDS：时间单位 
         */  
        scheduledExecutor.scheduleAtFixedRate(new timeTaskForException(), 1000, 2000, TimeUnit.MILLISECONDS);  
        scheduledExecutor.scheduleAtFixedRate(new timeTaskForPrintSYSTime(), 1000, 3000, TimeUnit.MILLISECONDS);  
          
    }  
  
    static class  timeTaskForException implements Runnable{  
  
        public void run() {  
            throw new RuntimeException();  
        }  
          
    }  
      
    static class timeTaskForPrintSYSTime implements Runnable {  
          
        public void run() {  
            System.out.println(System.nanoTime());  
        }  
    }  
}  