package com.yeahwell.dp.create.factorymothod2;

import com.yeahwell.dp.create.factorymothod1.Auto;

public class Client2 {

	public static void main(String[] args) {
		/**
		 * 调用工厂方法创建不同对象
		 * */
		Factory factory;
		
		Auto auto;
		
		factory = new CarFactory();
		auto = factory.createAuto();
		auto.run();
		
		factory = new BusFactory();
		auto = factory.createAuto();
		auto.run();
		
		factory = new TruckFactory();
		auto = factory.createAuto();
		auto.run();
	}
}
