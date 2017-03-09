package com.yeahwell.demo.thread.common.wjt09;

import java.util.concurrent.TimeUnit;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 1.10线程局部变量的使用

　　共享数据是并发程序最核心的问题之一，对于继承了Thread类或者实现了Runnable接口的对象来说尤其重要。 
　　如果创建的对象是实现了Runnable接口的类的实例，用它作为传入参数创建多个线程对象并启动这些线程，那么所有的线程将共享相同的属性。也就是说，如果你在一个线程中改变了一个属性，所有线程都会被这个改变影响. 
　　在某种情况下，这个对象的属性不需要被所有线程共享。Java并发API提供了一个干净的机制，即线程局部变量（Thread-Local Variable),其具有很好的性能。 

安全线程类的执行结果。现在，这3个线程对象都有它们自己的startDate属性值。 
　　线程局部变量分别为每个线程存储了各自的属性值.并提供给每个线程使用。你可以使用get()方法读取这个值，并用set()方法设置这个值。如果线程是第一次访问线程局部变量，线程局部变量可能还没有为它存储值，这个时候initialValue()方法就会被调用，并且返回当前的时间值。 
　　线程局部变量也提供了remove()方法，用来为访问这个变量的线程删除己经存储的值。 Java并发API包含了 InheritableThreadLocal类，如果一个线程是从其他某个线程中创建的， 这个类将提供继承的值。如果一个线程A在线程局部变量B有值，当它创建其他某个线程 B时，线程B的线程局部变量将跟线程A是一样的。可以覆盖childValue()方法，这个方法用来初始化子线程在线程局部变量中的值，它使用父线程在线程局部变量中的值作为传入参数。
 * @author yeahwell
 *
 */
public class SafeMain {
    public static void main(String[] args) {
        // 创建一个任务
        SafeTask task = new SafeTask();

        // 将任务放入三个不同的线程中运行
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(task);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }
}
