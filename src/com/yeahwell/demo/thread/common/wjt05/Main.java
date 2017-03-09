package com.yeahwell.demo.thread.common.wjt05;

import java.util.concurrent.TimeUnit;

/**
 * http://blog.csdn.　需要在某一个预期的时间中断线程的执行。例如，程序的一个线程每隔一分钟检査一次传感器状态，其余时间什么都不做。在这段空闲时间，线程不占用计 算机的任何资源。当它继续执行的CPU时钟来临时，JVM会选中它继续执行。可以通 过线程的sleep()方法来达到这个目标。　　 
　　sleep()方法接受整型数值作为参数，以表明线程 挂起执行的毫秒数。当线程休眠的时间结束了，JVM会分给它CPU时钟，线程将继续 执行它的指令。 
　　sleep()方法的另一种使用方式是通过TimeUnit枚举类元素进行调用。这个方法也使用Thread类的sleep()方法来使当前线程休眠，但是它接收的参数单位是秒，最后会被转化成毫秒。net/DERRANTCM/article/details/48101447
 * 
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个文件时间运行对象，并且将其放入一个线程对象中
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);

        // 开始线程
        thread.start();
        try {
            // 等待五秒
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断线程
        thread.interrupt();
    }
}
