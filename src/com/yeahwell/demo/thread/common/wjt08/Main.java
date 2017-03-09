package com.yeahwell.demo.thread.common.wjt08;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 1.9线程中不可控异常的处理

　　在Java中有两种异常。 
　　♦非运行时异常（Checked Exception）:这种异常必须在方法声明的throws语句指定，或者在方法体内捕获。例如：IOException和ClassNotFoundException。 
　　♦运行时异常（Unchecked Exception）:这种异常不必在方法声明中指定，也不需要在方法体中捕获。例如：NumberFormatException。 
　　因为run()方法不支持throws语句，所以当线程对象的run()方法抛出非运行异常时， 我们必须捕获并且处理它们。当运行时异常从run()方法中抛出时，默认行为是在控制台输出堆栈记录并且退出程序。 
　　Java提供给我们一种在线程对象里捕获和处理运行时异常的一种机制。

　　Thread类还有另一个方法可以处理未捕获到的异常，即静态方法setDefaultUncaughtExceptionHandler()。
	这个方法在应用程序中为所有的线程对象创建了一个异常处理器. 
　　当线程抛出一个未捕获到的异常时，JVM将为异常寻找以下三种可能的处理器。 
	首先，它査找线程对象的未捕获异常处理器。
	如果找不到，JVM继续査找线程对象所在的线程组（ThreadGroup)的未捕获异常处理器，
	如果还是找不到，JVM将继续查找默认的未捕获异常处理器. 
　　如果没有一个处理器存在，JVM则将堆栈异常记录打印到控制台，并退出程序。
 * @author yeahwell
 *
 */
public class Main {
	public static void main(String[] args) {
		
		Task task = new Task(); // 创建一个任务
		Thread thread = new Thread(task); // 创建一个线程
		thread.setUncaughtExceptionHandler(new ExceptionHandler()); // 设置线程的异常处理器
		thread.start();

		try {
			thread.join(); // 等待线程完成
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Thread has finished\n");
	}
}
