package com.yeahwell.demo.thread.comm;

import java.util.Random;

/**
 * ThreadLocal类及应用技巧
 *
 * 将线程范围内共享数据进行封装，封装到一个单独的数据类中，提供设置获取方法
 * 将该类单例化，提供获取实例对象的方法，获取到的实例对象是已经封装好的当前线程范围内的对象
 */
public class ThreadLocalTest {
	
	private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

	// private static ThreadLocal myThreadScopeData = new ThreadLocal ();
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()
							+ " has put data :" + data);
					x.set(data);
					/*
					 * MyThreadScopeData myData = new MyThreadScopeData();
					 * myData.setName("name" + data); myData.setAge(data);
					 * myThreadScopeData.set(myData);
					 */
					MyThreadScopeData.getThreadInstance()
							.setName("name" + data);
					MyThreadScopeData.getThreadInstance().setAge(data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}

	// 使用获取到的线程范围内的对象实例调用相应方法
	static class A {
		public void get() {
			int data = (Integer) x.get();
			System.out.println("A from " + Thread.currentThread().getName()
					+ " get data :" + data);
			/*
			 * MyThreadScopeData myData = myThreadScopeData.get();
			 * System.out.println("A from " + Thread.currentThread().getName() +
			 * " getMyData: " + myData.getName() + "," + myData.getAge());
			 */
			MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
			System.out
					.println("A from " + Thread.currentThread().getName()
							+ " getMyData: " + myData.getName() + ","
							+ myData.getAge());
		}
	}

	// 使用获取到的线程范围内的对象实例调用相应方法
	static class B {
		public void get() {
			int data = (Integer) x.get();
			System.out.println("B from " + Thread.currentThread().getName()
					+ " get data :" + data);
			MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
			System.out
					.println("B from " + Thread.currentThread().getName()
							+ " getMyData: " + myData.getName() + ","
							+ myData.getAge());
		}
	}
}

class MyThreadScopeData {
	// 单例
	private MyThreadScopeData() {
	}

	// 提供获取实例方法，不加synchronized关键字表示线程各拿各自的数据，互不干扰
	public static/* synchronized */MyThreadScopeData getThreadInstance() {
		// 从当前线程范围内数据集中获取实例对象
		MyThreadScopeData instance = (MyThreadScopeData) map.get();
		if (instance == null) {
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}

	// 将实例对象存入当前线程范围内数据集中
	private static ThreadLocal map = new ThreadLocal();
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}