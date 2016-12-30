package com.yeahwell.demo.jvm.memory;

/**
 * <h5>Java 栈内存溢出</h5>
 * VM arguments:  
 * -Xss2M
 * @author yeahwell
 * 本文件中的代码可能会导致操作系统假死。
 *
 */
public class JavaVMStackOOM {

	private void donnotStop(){
		while(true){
			
		}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					donnotStop();
				}
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) throws Throwable {
		JavaVMStackOOM com = new JavaVMStackOOM();
		com.stackLeakByThread();
	}
	
}
