package com.yeahwell.demo.thread.common.wjt12;

/**
 * http://blog.csdn.net/DERRANTCM/article/details/48101447
 * 1.13使用工厂类创建线程

　　工厂模式是面向对象编程中最常使用的模式之一.它是一个创建者模式，使用一个类为其他的一个或者多个类创建对象。当我们要为这些类创建对象时，不需再使用new构造 器，而使用工厂类。 
　　使用工厂类，可以将对象的创建集中化，这样做有以下的好处： 
　　♦更容易修改类，或者改变创建对象的方式i 
　　♦更容易为有限资源限制创建对象的数目，例如，可以限制一个类型的对象不多于1个。 
　　♦更容易为创建的对象生成统计数据。 
　　Java提供了 ThreadFactory接口，这个接口实现了线程对象工厂》Java并发API的高级工具类也使用了线程工厂创建线程。
 * @author yeahwell
 *
 */
public class Main {

	public static void main(String[] args) {
		// 创建一个线程工厂
		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
		// 创建一个任务
		Task task = new Task();
		Thread thread;

		// 创建并且启动10个线程对象
		System.out.printf("Starting the Threads\n");
		for (int i = 0; i < 10; i++) {
			thread = factory.newThread(task);
			thread.start();
		}
		// 打印线程工厂的统计信息
		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", factory.getStats());
	}
}
