package com.yeahwell.demo.j2se.core.io.property;

import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;

public class SystemProperties {

	public static void main(String[] args) {
		Properties prop = System.getProperties();
		
		//列出全部系统属性内容
		for(Entry<Object, Object> entry : prop.entrySet()){
			System.out.print("KEY : " + entry.getKey() + "\t");
			System.out.println("VALUE : " + entry.getValue());
		}
		
		//获得指定系统属性内容
		String version = prop.getProperty("java.version");
		System.out.println(version);
		
		String dir = prop.getProperty("user.dir");
		System.out.println(dir);
		
		//设置系统属性
		setProperty("mykey", "myvalue");
		System.out.println(prop.getProperty("mykey", "default"));
		
		//列出全部系统属性名
		Enumeration<String> names = (Enumeration<String>) prop.propertyNames();
		while(names.hasMoreElements()){
			String s = names.nextElement();
			System.out.println(s);
		}
	}
	
	private static void setProperty(String key, String value){
		Properties prop = System.getProperties();
		prop.setProperty(key, value);
	}
	
}
