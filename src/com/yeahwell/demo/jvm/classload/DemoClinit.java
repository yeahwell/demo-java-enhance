package com.yeahwell.demo.jvm.classload;

/**
 * 初始化阶段是执行类构造器&lt clinit &gt()方法的过程
 * <ol>
 * <li></li>
 * <li></li>
 * <li>父类的&lt clinit &gt()方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值操作</li>
 * <li>如果一个类中没有静态语句块，也没有对变量的赋值操作，那么编译器可以不为这个类生成&lt clinit &gt()方法</li>
 * </ol>
 * @author yeahwell
 *
 */
public class DemoClinit {

	static class Parent{
		public static int A = 1;
		static {
			A = 2;
		}
	}
	
	static class Sub extends Parent{
		public static int B = A;
	}
	
	public static void main(String[] args) {
		System.out.println(Sub.B);
	}
	
}
