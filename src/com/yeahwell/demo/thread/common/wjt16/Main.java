package com.yeahwell.demo.thread.common.wjt16;

/**
 * 
 * http://blog.csdn.net/derrantcm/article/details/48128815
 * 2.5使用锁实现同步

　　Java提供了同步代码块的另一种机制，它是一种比synchronized关键字更强大也更灵活的机制。这种机制基于Lock接口及其实现类（例如ReemrantLock)，提供了更多的好处。 
　　♦支持更灵活的同步代码块结构。使用synchronized关键字时，只能在同一个 synchronized块结构中获取和释放控制。Lock接口允许实现更复杂的临界区结构（即控制的获取和释放不出现在同一个块结构中）。 
　　♦相比synchronized关键字，Lock接口提供了更多的功能。其中一个新功能是 tryLock()方法的实现。这个方法试图获取锁，如果锁已被其他线程获取，它将返冋false 并继续往下执行代码。使用synchronized关键字时，如果线程A试图执行一个同步代码块， 而线程B已在执行这个同步代码块，则线程A就会被挂起直到线程B运行完这个同步代码块。使用锁的tryLock()方法，通过返回值将得知是否有其他线程正在使用这个锁保护的代码块。 
　　♦Lock接口允许分离读和写操作，允许多个读线程和只有一个写线程。 
　　♦相比synchronized关键字，Lock接口具有更好的性能。 
　　在本节中，我们将学习如何使用锁来同步代码，并且使用Lock接口和它的实现类—— ReentrantLock类来创建一个临界区。这个范例将模拟打印队列。

	这个范例的主要部分是打印队列类PrintQueue中的printJob()方法。我们使用锁实现 一个临界区，并且保证同一时间只有一个执行线程访问这个临界区时，必须创建 ReentrantLock对象。在这个临界区的开始，必须通过lock()方法获取对锁的控制。当线程A访问这个方法时，如果 没有其他线程获取对这个锁的控制，lock()方法将让线程A获得得锁并且允许它立刻执行临界区代码。否则，如果其他线程B正在执行这个锁保护的临界区代码，lock()方法将让线程A休眠直到线程B执行完临界区的代码。 
　　在线程离开临界区的时候，我们必须使用unlock()方法来释放它持有的锁，以让其他线程来访问临界区。如果在离开临界区的时候没有调用unlock方法，其他线程将永久地等待，从而导致死锁（Deadlock）情景。如果在临界区使用了try-catch块，不要忘记将unlock方法放入finally部分。 
　　Lock接口（和它的实现类ReentrantLock)还提供了另一个方法来获取锁，即tryLock() 方法。跟lock()方法最大的不同是：线程使用tryLock()不能够获取锁，tryLock()会立即返回，它不会将线程置入休眠。tryLock()方法返回一个布尔值，true表示线程获取了锁，false 表示没有获取锁。 
　　备注：编程人员应该重视tryLock()方法的返回值及其对应的行为。如果这个方法返回 false,则程序不会执行临界区代码。如果执行了，这个应用很可能会出现错误的结果。 
　　ReentrantLock类也允许使用递归调用。如果一个线程获取了锁并且进行了递归调用， 它将继续持有这个锁，因此调用lock()方法后也将立即返回，并且线程将继续执行递归调用。再者，我们还可以调用其他的方法。 
　　必须很小心使用锁，以避免死锁。当两个或者多个线程被阻塞并且它们在等待的锁永远不会被释放时，就会发生死锁。例如，线程A获取了锁X,线程B获取了锁Y,现在线程A试图获取锁Y，同时线程B也试图获取锁X,则两个线程都将被阻塞，而且它们等待的锁永远不会被释放。这个问题就在于两个线程都试图获取对方拥有的锁。

 * @author yeahwell
 *
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个打印队列
        PrintQueue printQueue = new PrintQueue();

        // 创建10个打印线程
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);
        }

        // 启动线程
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}