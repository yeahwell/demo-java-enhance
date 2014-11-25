package com.yeahwell.dp.behavior.observer1;

import java.util.Vector;

/**
 * 被监听的主题
 * @author yeahwell
 *
 */
public abstract class Subject {

	/**
	 * 用于存放监听器的集合，此集合线程安全
	 */
	private Vector<IObserver> observers;
	
	public Subject(){
		observers = new Vector<IObserver>();
	}
	
	/**
	 * 添加监听器,此方法需要同步
	 * @param observer
	 */
	public synchronized void addObserver(IObserver observer){
		observers.add(observer);
	}
	
	/**
	 * 删除监听器，此方法需要同步
	 * @param observer
	 */
	public synchronized void removeObserver(IObserver observer){
		observers.remove(observer);
	}
	
	/**
	 * 删除所有监听器，此方法需要同步
	 */
	public synchronized void removeObservers(){
		observers.clear();
	}
	
	/**
	 * 通知方法
	 * @param args
	 */
	public synchronized void notifyObservers(Object args){
		for(IObserver observer : observers){
			observer.update(this, args);
		}
	}
}
