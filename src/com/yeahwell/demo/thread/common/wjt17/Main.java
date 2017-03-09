package com.yeahwell.demo.thread.common.wjt17;

/**
 * http://blog.csdn.net/derrantcm/article/details/48128815
 * 2.6使用读写锁实现同步数据访问

　　锁机制最大的改进之一就是ReadWriteLock接口和它的唯一实现类ReentrantRead WriteLock。这个类有两个锁，一个是读操作锁，另一个是写操作锁。使用读操作锁时可 以允许多个线程同时访问，但是使用写操作锁时只允许一个线程进行。在一个线程执行写 操作时，其他线程不能够执行读操作。 
　　在本节中，我们将通过范例学习如何使用ReadWriteLock接口编写程序。这个范例将使用ReadWriteLock接口控制对价格对象的访问，价格对象存储了两个产品的价格。

　　ReentrantReadWriteLock类有两种锁：一种是读操作锁，另一 种是写操作锁。读操作锁是通过ReadWriteLock接口的readLock()方法获取的，这个锁 实现了 Lock接口，所以我们可以使用lock(), unlock()和tryLock()方法。写操作锁是通 过ReadWriteLock接口的writeLock()方法获取的，这个锁同样也实现了 Lock接口，所以我们也可以使用lock()、unlock()和tryLock()方法。编程人员应该确保正确地使用这些锁，使用它们的时候应该符合这些锁的设计初衷。当你获取Lock接口的读锁时，不可以进行修改操作，否则将引起数据不一致的错误。

 * @author yeahwell
 *
 */
public class Main {
	
    public static void main(String[] args) {
        // 创建价格信息对象，用于存储价格
        PricesInfo pricesInfo = new PricesInfo();

        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];

        // 创建5个读者并且将其放在不同的线程中远行
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }

        // 创建一个写者，并且将其放在一个线程中运行
        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        // 启动读者写者线程
        for (int i = 0; i < 5; i++) {
            threadsReader[i].start();
        }
        threadWriter.start();
    }
    
}