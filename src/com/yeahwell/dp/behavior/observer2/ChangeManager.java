package com.yeahwell.dp.behavior.observer2;

import java.util.Hashtable;
import java.util.Vector;

/**
 * 更改管理器（单例）
 * @author yeahwell
 *
 */
public class ChangeManager {

	/**
	 * 单例属性
	 */
	private static final ChangeManager cm = new ChangeManager();
	
	private static Hashtable<Subject,Vector<IObserver>> map = new Hashtable<Subject,Vector<IObserver>>();
	
	private ChangeManager(){
		
	}
	
	public static ChangeManager getInstance(){
		return cm;
	}
	
	/**
	 * 注册监听器
	 * @param subject
	 * @param observer
	 */
	public synchronized void register(Subject subject,IObserver observer){
		Subject key = subject;
		Vector<IObserver> observers = null;
		
		/**
		 * 查看是否已经注册此对象
		 */
		if(map.containsKey(key)){
			observers = map.get(key);
		}
		
		/**
		 * 查看此对象对应的监听器列表是否存在
		 */
		if(observers == null){
			observers = new Vector<IObserver>();
		}
		
		/**
		 * 差可能监听器是否重复
		 */
		if(!observers.contains(observer)){
			observers.add(observer);
		}
		
		/**
		 * 更新映射关系
		 */
		map.put(key, observers);
	}
	
	/**
	 * 解除监听器注册
	 * @param subject
	 * @param observer
	 */
	public synchronized void unregister(Subject subject,IObserver observer){
		map.get(subject).remove(observer);
	}
	
	/**
	 * 根据特定主题通知监听器
	 * @param subject
	 * @param args
	 */
	public synchronized void notifyBySubject(Subject subject,Object args){
		for(IObserver observer : map.get(subject)){
			observer.update(subject, args);
		}
	}
	
}
