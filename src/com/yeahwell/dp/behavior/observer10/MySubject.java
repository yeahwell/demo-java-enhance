package com.yeahwell.dp.behavior.observer10;

public class MySubject extends AbstractSubject{

	@Override
	public void operation() {
		System.out.println("update self!");
		notifyObservers();
	}

}
