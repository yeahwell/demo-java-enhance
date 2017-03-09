package com.yeahwell.demo.thread.common.wjt19;


/**
 * http://blog.csdn.net/derrantcm/article/details/48128815
 * 2.8在锁中使用多条件（Multiple Condition)

　　一个锁可能关联一个或者多个条件，这些条件通过Condition接口声明。目的是允许线程获取锁并且査看等待的某一个条件是否满足，如果不满足就挂起直到某个线程唤醒它们。Condition接口提供了挂起线程和唤起线程的机制。 
　　并发编程中的一个典型问题是生产者-消费者（Producer-Consumer)问题。如本章前面提到的，我们使用一个数据缓冲区，一个或者多个数据生产者（Producer)将数据保存 到缓冲区，一个或者多个数据消费者（Consumer)将数据从缓冲区中取走。 
在本节中，我们将通过范例学习并使用锁和条件.来解决生产者-消费者问题。

与锁绑定的所有条件对象都是通过Lock接口声明的nenConditionO方法创建的。在使用条件的时候，必须获取这个条件绑定的锁，所以带条件的代码必须在调用lock对象的lock()方法和unlock()方法之间。 
　　当线程调用条件的await()方法时，它将自动释放这个条件绑定的锁，其他某个线程才可以获取这个锁并且执行相同的操作，或者执行这个锁保护的另一个临界区代码。 
　　备注：当一个线程调用了条件对象的signal()或者signallAll()方法后，一个或者多个在该条件上挂起的线程将被唤醒，但这并不能保证让它们挂起的条件己经满足，所以必须在while循环中调用await(),在条件成立之前不能离开这个循环。如果条件不成立，将再次调用await()。 
　　必须小心使用await()和signal()方法。如果调用了一个条件的await()方法，却从不调用它的signal()方法，这个线程将永久休眠。 
　　因调用await()方法进入休眠的线程可能会被中断，所以必须处理InterruptedException异常。 
　　Condition接口还提供了 await()方法的其他形式。 
　　await(long time, TimeUnit unit),直到发生以下情况之一之前，线程将一直处于休眠状态。 
　　♦其他某个线程中断当前线程。 
　　♦其他某个线程调用了将当前线程挂起的条件的singal()或signalAll()方法。 
　　♦指定的等待时间已经过去。 
　　♦通过 TimeUnit 类的常量 DAYS、HOURS、MICROSECONDS、MILLISECONDS、 MINUTES、ANOSECONDS和SECONDS指定的等待时间已经过去， 
　　awaitUninterruptibly():它是不可中断的。这个线程将休眠直到其他某个线程调用了将它挂起的条件的singal()或signalAll()方法。 
awaitUntil(Date date):直到发生以下情况之一之前，线程将一直处于休眠状态。 
　　♦其它某个线程调用了将它挂起的条件的singal()或者signalAll()方法。 
　　♦其他某个线程中断当前线程。 
　　♦指定的最后期限到了。 
也可以将条件与读写锁ReadLock和Writelock —起使用。
 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个文件模拟对象，它有101行
        FileMock mock = new FileMock(101, 10);

        // 创建一个缓冲对象，最多可以缓存20行数据
        Buffer buffer = new Buffer(20);

        // 创建一个生产者对象，并且让它在一个单独的线程中运行
        Producer producer = new Producer(mock, buffer);
        Thread threadProducer = new Thread(producer, "Producer");

        // 创建三个消费者对象，并且个他们分别在不同的线程中运行
        Consumer consumers[] = new Consumer[3];
        Thread threadConsumers[] = new Thread[3];

        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
        }

        // 启动生产者和消费者线程
        threadProducer.start();
        for (int i = 0; i < 3; i++) {
            threadConsumers[i].start();
        }
    }
}