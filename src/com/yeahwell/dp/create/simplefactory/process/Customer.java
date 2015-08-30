package com.yeahwell.dp.create.simplefactory.process;

public class Customer {

	public static void main(String[] args) {
		BMW bmw320 = Factory.createBMW(320);
		BMW bmw523 = Factory.createBMW(523);
	}

}
