package com.yeahwell.dp.structure.adapter1;

//具体目标类，只提供普通功能  
public class ConcreteTarget implements Target {
	public void request() {
		System.out.println("普通类 具有 普通功能...");
	}
}
