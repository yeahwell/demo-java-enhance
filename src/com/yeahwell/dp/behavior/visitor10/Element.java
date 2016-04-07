package com.yeahwell.dp.behavior.visitor10;

public abstract class Element {

	public abstract void accept(IVisitor visitor);
	
	public abstract void doSomething();
	
}
