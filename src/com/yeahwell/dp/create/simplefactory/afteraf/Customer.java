package com.yeahwell.dp.create.simplefactory.afteraf;

public class Customer {
	public static void main(String[] args) {
		// 生产宝马320系列配件
		AbstractFactory factoryBMW320 = new FactoryBMW320();
		factoryBMW320.createEngine();
		factoryBMW320.createAircondition();

		// 生产宝马523系列配件
		AbstractFactory factoryBMW523 = new FactoryBMW523();
		factoryBMW523.createEngine();
		factoryBMW523.createAircondition();
		
	}
	
}