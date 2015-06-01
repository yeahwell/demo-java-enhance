package com.yeahwell.demo.grammer;

public class TestPlus {

	public static void main(String[] args) {
		int product = 1;
		for(int i = 10; i <= 99; i++){
			product *= i;
		}
		//当i = 42时，product = 0
		System.out.println(product);
	}
	
}
