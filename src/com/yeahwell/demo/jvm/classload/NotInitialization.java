package com.yeahwell.demo.jvm.classload;


/**
 * 非主动使用类字段演示
 * @author yeahwell
 *
 */
public class NotInitialization {

	public static void main(String[] args) {
		
		//被动引用例子1
		System.out.println(SubClass.value);
		//被动引用例子2
//		SuperClass[] sca = new SuperClass[10];
		//被动引用例子3
//		System.out.println(ConstClass.HELLO_WORLD);
	}
	
}
