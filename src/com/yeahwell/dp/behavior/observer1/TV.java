package com.yeahwell.dp.behavior.observer1;

public class TV implements IObserver{

	@Override
	public void update(Subject subject, Object args) {
		System.out.println("TV 获得了新消息 [" + args + "]");
	}

}
