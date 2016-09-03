package com.yeahwell.demo.j2se.core.io.file;

import java.io.File;

public class Demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("/Users/yeahwell/git/demo-java-enhance/testconfig.properties");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getParent());
		System.out.println(file.getPath());
		System.out.println(file.getName());
		
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.isAbsolute());
		System.out.println(file.exists());
	}
	
	
}
