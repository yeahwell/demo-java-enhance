package com.yeahwell.dp.behavior.observer2;

/**
 * 监听器接口
 * @author yeahwell
 *
 */
public interface IObserver {

	public void update(Subject subject,Object args);
	
}
