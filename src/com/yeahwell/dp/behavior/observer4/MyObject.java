package com.yeahwell.dp.behavior.observer4;

import java.util.Vector;

public class MyObject {

	/**
	 * 如果需要监听多种事件需要使用Map
	 */
	private Vector<IMyEventListener> el = new Vector<IMyEventListener>();

	/**
	 * 增加监听器
	 * @param myEventListener
	 */
	public void addListener(IMyEventListener myEventListener){
		if(!el.contains(myEventListener)){
			el.add(myEventListener);
		}
	}
	
	/**
	 * 删除监听器
	 */
	public void removeListener(IMyEventListener myEventListener){
		el.remove(myEventListener);
	}
	
	/**
	 * 通知事件
	 * @param myEvent
	 */
	public void notifyEvent(MyEvent myEvent){
		for(IMyEventListener myEventListener : el){
			myEventListener.doListener(myEvent);
		}
	}
	
	/**
	 * 触发事件
	 */
	public void doEvent(){
		notifyEvent(new MyEvent(this));
	}
}
