package com.yeahwell.dp.create.single;

/**
 * 饿汉式单例类.在类初始化时，已经自行实例化
 * 饿汉式在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以天生是线程安全的。
 * @author yeahwell
 *
 */
public class SingletonHungry {

	private SingletonHungry() {
	}

	private static final SingletonHungry single = new SingletonHungry();

	// 静态工厂方法
	public static SingletonHungry getInstance() {
		return single;
	}

}
