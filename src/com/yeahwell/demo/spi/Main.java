package com.yeahwell.demo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {

	public static void main(String[] args) {
		IOperation plus = new PlusOperationImpl();
		IOperation division = new DivisionOperationImpl();
		System.out.println(plus.operation(5, 3));
		System.out.println(division.operation(9, 3));
		
		ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);
		Iterator<IOperation> iterator = operations.iterator();
		System.out.println("classPath:" + System.getProperty("java.class.path"));
		while(iterator.hasNext()){
			IOperation operation = iterator.next();
			System.out.println(operation.operation(6, 3));
		}
	}

}
