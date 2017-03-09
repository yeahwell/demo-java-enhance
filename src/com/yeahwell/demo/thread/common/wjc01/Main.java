package com.yeahwell.demo.thread.common.wjc01;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 　当调用Thread对象的start()方法时，另一个执行线程将被创建。因而在我们的程序中， 每次调用start()方法时，都会创建一个执行线程。 
　　当一个程序的所有线程都运行完成时，更明确的说，当所有非守护(non-daemon)线程 都运行完成的时候，这个Java程序将宣告结束。如果初始线程（执行main()方法的线程） 结束了，其余的线程仍将继续执行直到它们运行结束。如果某一个线程调用了 SyStem.exit()指令来结束程序的执行，所有的线程都将结束。 
　　对一个实现了Runnable接U的类来说，创建Thread对象并不会创建一个新的执行线程；同样的，调用它的run()方法，也不会创建一个新的执行线程。只有调用它的start()方 法时，才会创建一个新的执行线程。 
　　还有另一种方法能够创建新的执行线程。编写一个类并继承 Thread类，在这个类里覆盖run()方法，然后创建这个类的对象，并且调用start()方法，也会创建一个执行线程。
 * @author yeahwell
 *
 */
public class Main {

	public static void main(String[] args) {
		// 创建一个执行10次的循环。在每次循环中创建一个Calculator 对象，
		// 一个Thread对象，这个Thread对象使用刚创建的Calculator对象作为构造器的参数，
		// 然后调用刚创建的Thread对象的start()方法。
		for (int i = 0; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}
	
}