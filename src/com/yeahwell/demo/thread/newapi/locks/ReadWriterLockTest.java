package com.yeahwell.demo.thread.newapi.locks;

import java.util.Random;  
import java.util.concurrent.locks.Lock;  
import java.util.concurrent.locks.ReadWriteLock;  
import java.util.concurrent.locks.ReentrantReadWriteLock;  
  
public class ReadWriterLockTest {  
    public static void main(String[] args) {  
        final Queue q3 = new Queue();  
        for(int i=0;i<3;i++)  
        {  
            new Thread(){  
                public void run(){  
                    while(true){  
                        q3.get();                         
                    }  
                }  
                  
            }.start();  
        }  
        for(int i=0;i<3;i++)  
        {         
            new Thread(){  
                public void run(){  
                    while(true){  
                        q3.put(new Random().nextInt(10000));  
                    }  
                }             
                  
            }.start();  
   }  
    }  
}  
  
  
class Queue{  
    //共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。  
    private Object data = null;  
    //得到读写锁  
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();  
      
    /** 
     * 将用于读的get()和写的put()放在同一个类中这样是为了对同一个资源data进行操作，形成互斥 
     */  
      
    /** 
     * 进行读操作 
     * 可以多个读线程同时进入，写线程不能执行 
     */  
    public void get(){  
        //获取读锁，并加锁  
        Lock readLock = readWriteLock.readLock();  
        readLock.lock();  
        try {  
            System.out.println(Thread.currentThread().getName() + " be ready to read data!");  
            Thread.sleep((long)(Math.random()*1000));  
            System.out.println(Thread.currentThread().getName() + "have read data :"+ data);          
              
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }finally {  
            //!!!!!!注意：锁的释放一定要在trycatch的finally中，因为如果前面程序出现异常，锁就不能释放了  
            //释放读锁  
            readLock.unlock();  
        }  
  
          
    }  
      
    /** 
     * 进行写操作 
     * 只能一个写线程进入，读线程不能执行 
     */  
    public void put(Object data){  
        //获取写锁，并加锁  
        Lock writeLock = readWriteLock.writeLock();  
        writeLock.lock();  
        try {  
            System.out.println(Thread.currentThread().getName() + " be ready to write data!");                    
            Thread.sleep((long)(Math.random()*1000));  
            this.data = data;         
            System.out.println(Thread.currentThread().getName() + " have write data: " + data);   
              
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }finally {  
            //释放写锁  
            writeLock.unlock();  
        }  
          
    }  
}  
