package com.yeahwell.dp.create.factorymothod1;

public class Bus extends Auto{

	public Bus(){
		this.setName("巴士");
	}

	@Override
	public void run() {
		System.out.println("启动" + this.getName());
	}
	
	
}
