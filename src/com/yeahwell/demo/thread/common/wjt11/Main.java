package com.yeahwell.demo.thread.common.wjt11;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 1.12线程组中不可控异常的处理

　　建立一个方法来捕获线程组中的任何线程对象抛出的非捕获异常。

　运行时.你会看到当一个线程对象抛出了异常，其余的线程对象都被中断. 当线程抛出非捕获异常时，JVM将为这个异常寻找3种可能的处理器. 
　　首先，寻找抛出这个异常的线程的非捕获异常处理器，
	如果这个处理器不存在，JVM继续査找这个线程所在的线程组的非捕获异常处理器。
	如果也不存在JVM将寻找默认的非捕获异常处理器，
	如果这些处理器都不存在，JVM将把堆栈中异常信息打印到控制台，并且退出这个程序。
 * @author yeahwell
 *
 */
public class Main {

    public static void main(String[] args) {
        // 创建一个自定义的线程组
        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
        // 创建一个任务
        Task task = new Task();
        // 创建两个线程，将其放入同一个线程组中，并且执行同一个任务
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(threadGroup, task);
            t.start();
        }
    }
}