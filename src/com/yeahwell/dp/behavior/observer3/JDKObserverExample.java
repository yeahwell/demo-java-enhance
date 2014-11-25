package com.yeahwell.dp.behavior.observer3;

public class JDKObserverExample {

	public static void main(String[] args) {
		MySubject subject = new MySubject();
		
		subject.addObserver(new MyObserver());
		
		subject.setContent("下雨收衣服了~");
	}
}
