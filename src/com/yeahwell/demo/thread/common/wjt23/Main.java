package com.yeahwell.demo.thread.common.wjt23;

import java.util.concurrent.CyclicBarrier;

/**
 * http://blog.csdn.net/derrantcm/article/details/48152207
 * 3.5在集合点的同步

　　Java并发API提供了 CycleBarrie类，它也是一个同步辅助类。它允许两个或者多个线程在某个点上进行同步。这个类与上一节所讲述的CountDownLatch类类似，但也有不同之处，使之成为更强大的类。

　　CycleBarrie类使用一个整型数进行初始化，这个数是需要在某个点上同步的线程数。当一个线程到达指定的点后，它将调用await()方法等待其他的线程。当线程调用mvaitO 方法后，CycleBarrie类将阻塞这个线程并使之休眠直到所有其他线程到达。当最后一个 线程调用CycleBarrie类的await()方法时，CycleBarrie对象将唤醒所有在等待的线程，然后这些线程将继续执行。

　　CycleBarrie类有一个很有意义的改进，即它可以传入另一个Runnable对象作为初始化参数。当所有的线程都到达集合点后，CycleBarrie类将这个Runnable对象作为线程执行。这个特性使得这个类在并行任务上可以媲美分治编程技术（Divide and Conquer Programming Technigue)。

　　在本节中，我们将学习如何使用CyclicBarrier类使一组线程在集合点上同步。在所有线程都到达集合点后，我们将使用Rumiable对象并且运行它。在这个范例中，我们将在数字矩阵中寻找一个数字（使用分治编程技术)。这个矩阵会被分成几个子集，然后每个线程在一个子集中査找。一旦所有线程都完成查找，最终的任务将统一这些结果。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {

        final int ROWS = 10000; // 矩阵的行数
        final int NUMBERS = 1000; // 矩阵的列数
        final int SEARCH = 5; // 要查询的数字
        final int PARTICIPANTS = 5; // 查询线程的个数
        final int LINES_PARTICIPANT = 2000; // 每个查询线程处理的行数
        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH); // 创建矩阵模拟对象
        Results results = new Results(ROWS); // 创建结果对象
        Grouper grouper = new Grouper(results); // 创建组合对象

        // 创建一个同步栅对象，它有5个参与者， 5个参与者线程完成后，会调用grouper中的run方法
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

        // 创建5个参与者对象，并且让它们各自在单独的线程中运行
        Searcher[] searchers = new Searcher[PARTICIPANTS];
        for (int i = 0; i < searchers.length; i++) {
            searchers[i] = new Searcher(barrier, i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT,
                    mock, results, PARTICIPANTS);

            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished.\n");
    }
}