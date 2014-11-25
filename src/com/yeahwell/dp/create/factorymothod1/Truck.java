package com.yeahwell.dp.create.factorymothod1;

public class Truck extends Auto{

	public Truck(){
		this.setName("卡车");
	}

	@Override
	public void run() {
		System.out.println("启动" + this.getName());
	}
	
	
}
