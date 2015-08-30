package com.yeahwell.dp.create.single;

public class SingletonFull2 {
	private SingletonFull2() {
	}

	private static SingletonFull2 single = null;

	public static synchronized SingletonFull2 getInstance() {
		// 2、双重检查锁定
		synchronized (SingletonFull.class) {
			if (single == null) {
				single = new SingletonFull2();
			}
		}
		return single;
	}

}