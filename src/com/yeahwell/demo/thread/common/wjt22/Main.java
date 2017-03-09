package com.yeahwell.demo.thread.common.wjt22;

/**
 * http://blog.csdn.net/derrantcm/article/details/48152207
 * 3.4等待多个并发事件的完成

　　Java并发API提供了CountDownLatch类，它是一个同步辅助类。在完成一组正在其他线程中执行的操作之前，它允许线程一直等待。这个类使用一个整数进行初始化，这个整数就是线程要等待完成的操作的数目。当一个线程要等待某些操作先执行完时，需要调用await()方法，这个方法让线程进入体眠直到等待的所有操作都完成。当某一个操作完成后，它将调用countDown()方法将CountDownLatch类的内部计数器减1。当计数器变成0的时候，CountDownLatch类将唤醒所有调用await()方法而进入休眠的线程。

　　在本节中，我们将学习如何使用CountDomiLatch类实现视频会议系统。这个视频会议系统将等待所有的参会者都到齐才开始。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个视频会议对象，它有10个参与者
        VideoConference conference = new VideoConference(10);
        // 创建一个线程去运行视频会议
        Thread threadConference = new Thread(conference);
        threadConference.start();

        // 创建十个参与者，每个参与者在一个单独的线程中运行
        for (int i = 0; i < 10; i++) {
            Participant p = new Participant(conference, "Participant " + i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}