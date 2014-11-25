package com.yeahwell.dp.behavior.observer3;

import java.util.Observable;

public class MySubject extends Observable{

	private String content;

	public void setContent(String content) {
		this.content = content;
		
		/**
		 * 表示内容已经更改需要通知
		 */
		this.setChanged();
		
		/**
		 * 调用方法
		 */
		this.notifyObservers(content);
	}
	
	
}
