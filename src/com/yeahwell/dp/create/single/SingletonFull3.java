package com.yeahwell.dp.create.single;

/**
 * 既实现了线程安全，又避免了同步带来的性能影响
 * @author yanjiawei
 *
 */
public class SingletonFull3 {

	private static class LazyHolder {
		private static final SingletonFull3 INSTANCE = new SingletonFull3();
	}

	private SingletonFull3() {
	}

	public static final SingletonFull3 getInstance() {
		return LazyHolder.INSTANCE;
	}

}