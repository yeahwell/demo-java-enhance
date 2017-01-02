package com.yeahwell.demo.jvm.gc;

/**
 * 引用计数
 * @author yeahwell
 *
 */
public class ReferenceCountingGC {

	public Object instance = null;
	
	private static final int _1MB = 1024 * 1024;
	
	/**
	 * 用来占内存，以便在GC日志中查看是否被回收过
	 */
	private byte[] bigSize = new byte[2 * _1MB];
	
	public static void testGC(){
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;
		
		objA = null;
		objB = null;
		
		//假设在这里发生GC，objA和objB能否被回收
		System.gc();
	}
	
	public static void main(String[] args) {
		testGC();
	}
	
}
