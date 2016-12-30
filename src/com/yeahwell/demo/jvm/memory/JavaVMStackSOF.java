package com.yeahwell.demo.jvm.memory;

/**
 * <h5>Java 栈内存溢出</h5>
 * VM arguments 
 * -Xss128k
 * @author yeahwell
 *
 */
public class JavaVMStackSOF {

	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) throws Throwable {
		JavaVMStackSOF com = new JavaVMStackSOF();
		try{
			com.stackLeak();
		}catch(Throwable e){
			System.out.println("stack length : " + com.stackLength);
			throw e;
		}
		
	}
	
}
