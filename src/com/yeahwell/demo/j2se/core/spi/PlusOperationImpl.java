package com.yeahwell.demo.j2se.core.spi;

public class PlusOperationImpl implements IOperation {
	
	@Override
	public int operation(int numberA, int numberB) {
		return numberA + numberB;
	}
	
}