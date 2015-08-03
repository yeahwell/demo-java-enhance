package com.yeahwell.demo.grammer.lock;

/**
 * 
 * 
如果一个线程在两次调用lock()间，没有调用unlock()方法，那么第二次调用lock()就会被阻塞，这就出现了重入锁死。
避免重入锁死有两个选择：
编写代码时避免再次获取已经持有的锁
使用可重入锁
 * @author yeahwell
 *
 */
public class UnReentrantLock {

	private boolean isLocked = false;
	
	public synchronized void lock()
		throws InterruptedException{
		while(isLocked){
			wait();
		}
		isLocked = true;
	}

	public synchronized void unlock(){
		isLocked = false;
		notify();
	}
	
}
