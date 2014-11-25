package com.yeahwell.demo.jni;

public class TestNative {

	public native void sayHello();
	
	public static void main(String[] args) {
		System.loadLibrary("NativeCode");  //加载动态链接库
		TestNative tn = new TestNative();
		tn.sayHello();
	}
}
