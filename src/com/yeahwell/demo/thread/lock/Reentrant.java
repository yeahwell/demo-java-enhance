package com.yeahwell.demo.thread.lock;

/**
 * 当一个线程重新获取锁，读写锁或其他不可重入的同步器时，就可能发生重入锁死。
 * 可重入的意思是线程可以重复获得它已经持有的锁。Java的synchronized块是可重入的。
 * 
 * 
 * 如果一个线程持有某个管程对象上的锁，那么它就有权访问所有在该管程对象上同步的块。这就叫可重入。
 * 若线程已经持有锁，那么它就可以重复访问所有使用该锁的代码块。
 * @author yanjiawei
 *
 */
public class Reentrant{
	
	/**
	 * 这里两个方法是实例方法，synchronized的实例方法相当于在this上加锁，如果是static方法，则不然
	 */
	public synchronized void outer(){
		inner();
	}

	public synchronized void inner(){
		//do something
	}
}
