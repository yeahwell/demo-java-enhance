package com.yeahwell.dp.behavior.observer10;

public interface Subject {

	/**
	 * 增加观察者
	 * @param observer
	 */
	public void add(Observer observer);
	
	/**
	 * 删除观察者
	 * @param observer
	 */
	public void del(Observer observer);
	
	/**
	 * 通知所有的观察者*/
	public void notifyObservers();
	
	/**
	 *  自身的操作*/
	public void operation();
	
}
