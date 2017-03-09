package com.yeahwell.demo.thread.common.wjc02;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 　Thread类的属性存储了线程的所有信息。JVM使用线程的priority属性来决定某一刻 由哪个线程來使用CPU,并且根据线程的情景为它们设置实际状态。 
　　如果没有为线程指定一个名字，JVM将自动给它分配一个名字，格式是Thread-XX, 其中XX是一组数字。线程的ID和状态是不允许被修改的，线程类没有提供setId()和 setStatus()方法来修改它们。 
　　通过Thread对象访问属性信息。也可以通过实现 Runnable接口的对象来访问这些属性信息。如果一个线程是以Runnable对象为参数构建的， 那么也可以使用Thread类的静态方法currentThread()来访问这个线程对象。 
　　如果使用setPriority()方法设置的优先级不是从1到10这个范围内的值， 运行时就会抛出IllegalArgumentException异常。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {

        // 线程优先级信息
        System.out.printf("Minimum Priority: %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Normal  Priority: %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Maximum Priority: %s\n", Thread.MAX_PRIORITY);

        Thread threads[];
        Thread.State status[];

        // 运行10个线程，5个线程的使用最高优先级，5个线程使用最低优先级
        threads = new Thread[10];
        status = new Thread.State[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Calculator(i));
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread " + i);
        }

        // 等待线程完成，同时将线程状态信息写入到文件中
        PrintWriter pw = null;
        try {
            // 获取项目运行的根路径
            String configFile = Main.class.getClassLoader().getResource("").getPath();
            configFile = URLDecoder.decode(configFile, "utf-8");

            System.out.println(configFile);

            File logFile = new File(configFile + "/data/log.txt"); // 创建一个记录文件对象

            if(!logFile.getParentFile().exists()) {    // 如果目录不存在就创建目录
                logFile.getParentFile().mkdirs();
            }

            if (!logFile.exists()) { //如果文件不存在就创建一个文件
                logFile.createNewFile();
            }

            FileWriter file = new FileWriter(logFile);
            pw = new PrintWriter(file);

            for (int i = 0; i < 10; i++) {
                pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
                status[i] = threads[i].getState();
            }

            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) { // 如果线程状态发生了变化
                        writeThreadInfo(pw, threads[i], status[i]); // 将线程变化之前的状态写入文件
                        status[i] = threads[i].getState(); // 记录新的状态
                    }
                }

                finish = true;
                for (int i = 0; i < 10; i++) {
                    // 如果所有线程都终止了finish就为true
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            } 
        }
    }

    /**
     * 将线程状态信息写入到一个文件中
     *
     * @param pw     写数据的流
     * @param thread 信息要被写入文件的线程
     * @param state  线程的前一个状态
     */
    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d ---- %s\n", thread.getId(), thread.getName());
        pw.printf("Main : Priority:  %d\n", thread.getPriority());
        pw.printf("Main : Old State: %s\n", state);
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.printf("Main : ************************************\n");

    }
}