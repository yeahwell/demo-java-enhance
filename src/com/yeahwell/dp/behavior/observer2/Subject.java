package com.yeahwell.dp.behavior.observer2;

/**
 * 抽象主题类
 * @author yeahwell
 *
 */
public abstract class Subject {

	/**
	 * 保存一个更改管理器
	 */
	private static final ChangeManager changeManager = ChangeManager.getInstance();

	public static ChangeManager getChangeManager() {
		return changeManager;
	}
 
	/**
	 * 增加监听器
	 * @param observer
	 */
	public void addObserver(IObserver observer){
		getChangeManager().register(this, observer);
	}
	
	/**
	 * 移除监听器
	 * @param observer
	 */
	public void removeObserver(IObserver observer){
		getChangeManager().unregister(this, observer);
	}
	
	/**
	 * 通知更新
	 * @param args
	 */
	public void notify(Object args){
		getChangeManager().notifyBySubject(this, args);
	}
}
