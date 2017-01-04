package com.yeahwell.dp.create.single;

public class Singleton {

	private Singleton(){
		System.out.println("hello 单例模式");
	}
	
	private static Singleton instance = new Singleton();
	
	public static Singleton getInstance(){
		return instance;
	}
	
	public static void main(String[] args) {
		System.out.println(Singleton.getInstance());
	}
	
	
}
