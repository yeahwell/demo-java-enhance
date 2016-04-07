package com.yeahwell.dp.behavior.visitor10;

public class ConcreteElement1 extends Element{

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void doSomething() {
		System.out.println("这是元素1");
	}

}
