package com.yeahwell.demo.jvm.classload;

/**
 * <h5>被动引用的例子</h5>
 * 通过子类引用父类的静态字段，不会导致子类初始化
 * @author yeahwell
 *
 */
public class SuperClass {

	static {
		System.out.println("SuperClass init");
	}
	
	public static int value = 123;
	
}
