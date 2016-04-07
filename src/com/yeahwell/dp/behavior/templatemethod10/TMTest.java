package com.yeahwell.dp.behavior.templatemethod10;

public class TMTest {

	public static void main(String[] args) {
		String exp = "8+8";
		AbstractCalculator cal = new Plus();
		int result = cal.calculate(8,  8);
		System.out.println(result);
		
	}
	
}
