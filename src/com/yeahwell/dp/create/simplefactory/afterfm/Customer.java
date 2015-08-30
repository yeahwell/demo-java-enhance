package com.yeahwell.dp.create.simplefactory.afterfm;

public class Customer {

	public static void main(String[] args) {
		FactoryBMW factory = new FactoryBMW320();
		factory.createBMW();
		
		factory = new FactoryBMW523();
		factory.createBMW();
		
	}

}
