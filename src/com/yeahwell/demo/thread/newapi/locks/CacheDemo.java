package com.yeahwell.demo.thread.newapi.locks;

import java.util.HashMap;  
import java.util.Map;  
import java.util.Random;  
import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReadWriteLock;  
import java.util.concurrent.locks.ReentrantReadWriteLock;  

/**
 * http://blog.csdn.net/truelove12358/article/details/53484705
 * @author yeahwell
 *
 */
public class CacheDemo {  
    //用map来模拟缓存  
    Map<String,Object> cache = new HashMap<String,Object>();  
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();  
  
      
    public static void main(String[] args) {  
        final CacheDemo cacheDemo = new CacheDemo();  
        for(int i=0;i<6;i++)  
        {  
            new Thread(){  
                public void run(){  
                    while(true){  
                      
                        System.out.println( cacheDemo.getData("key1").toString());  
                    }  
                }  
                  
            }.start();  
        }  
          
    }  
      
      
    Lock readLock = readWriteLock.readLock();  
    Lock writeLock = readWriteLock.writeLock();  
    //这里必须要用volatie当一个写线程设置value="aaaabbbb"，一定要让其他的线程知道vlue的变化，这样就不会被重复写  
    volatile Object value;  
    public Object getData(String key){  
          
        readLock.lock();  
          
        try {  
            Thread.sleep(300);  
            System.out.println(" read");  
             value = cache.get(key);  
            if (value == null) {  
                  
                //这里已经加了读锁，读锁中写是不能允许的，所以要把这个锁释放掉  
                readLock.unlock();  
                writeLock.lock();  
                //防止，当多个写者进程在等待，前面的写进程已经赋值了，value已经不为空了后面的等着的写进程仍然继续赋值  
                if(value == null){  
                    System.out.println("find null");  
                    
                    value="aaaabbbb";  
                    cache.put(key, value);  
                    System.out.println("write");  
                }  
                  
                  
                writeLock.unlock();  
                //从新加上读锁  
                readLock.lock();  
                  
            }  
            return value;  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally {  
            readLock.unlock();  
        }  
  
        return null;  
      
    }  
      
}  