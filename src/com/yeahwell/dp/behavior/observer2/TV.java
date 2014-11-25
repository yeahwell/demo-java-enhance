package com.yeahwell.dp.behavior.observer2;

public class TV implements IObserver{

	@Override
	public void update(Subject subject, Object args) {
		System.out.println("TV 获得了新消息 [" + args + "]");
	}

}
