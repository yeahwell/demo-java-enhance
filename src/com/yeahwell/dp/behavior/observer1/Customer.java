package com.yeahwell.dp.behavior.observer1;

public class Customer implements IObserver{

	@Override
	public void update(Subject subject, Object args) {
		System.out.println("Customer 获得了新消息 [" + args + "]");
	}

	
}
