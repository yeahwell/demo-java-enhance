package com.yeahwell.dp.create.single;

public class SingletonFull1 {
	private SingletonFull1() {
	}

	private static SingletonFull1 single = null;

	public static synchronized SingletonFull1 getInstance() {
		if (single == null) {
			single = new SingletonFull1();
		}
		return single;
	}
	

}