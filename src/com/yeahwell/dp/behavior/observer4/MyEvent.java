package com.yeahwell.dp.behavior.observer4;

import java.util.Date;
import java.util.EventObject;

/**
 * 自定义事件
 * @author yeahwell
 *
 */
public class MyEvent extends EventObject{

	public String getMsg(){
		return "新的时间通知" + new Date().toLocaleString();
	}
	
	/**
	 * 构造方法
	 * @param source 产生事件的源对象
	 */
	public MyEvent(Object source) {
		super(source);
	}

}
