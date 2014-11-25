package com.yeahwell.dp.create.factorymothod1;

public class Car extends Auto{

	public Car(){
		this.setName("轿车");
	}

	@Override
	public void run() {
		System.out.println("启动" + this.getName());
	}
	
	
}
