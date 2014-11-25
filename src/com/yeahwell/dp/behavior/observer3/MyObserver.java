package com.yeahwell.dp.behavior.observer3;

import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("收到来自" + o.getClass().getName() + " : " + arg);
	}

}
