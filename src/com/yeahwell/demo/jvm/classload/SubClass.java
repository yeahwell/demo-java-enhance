package com.yeahwell.demo.jvm.classload;


/**
 * 
 * @author yeahwell
 *
 */
public class SubClass extends SuperClass{

	static {
		System.out.println("SubClass init");
	}
	
}
