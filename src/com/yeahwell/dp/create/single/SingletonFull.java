package com.yeahwell.dp.create.single;

//懒汉式单例类.在第一次调用的时候实例化自己 
public class SingletonFull {
	private SingletonFull() {
	}

	private static SingletonFull single = null;

	// 静态工厂方法
	public static synchronized SingletonFull getInstance() {
		synchronized (SingletonFull.class) {   
			if (single == null) {
				single = new SingletonFull();
			}
		}
		return single;
	}
}