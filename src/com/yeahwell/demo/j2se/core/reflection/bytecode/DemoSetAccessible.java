package com.yeahwell.demo.j2se.core.reflection.bytecode;

import java.lang.reflect.Field;

public class DemoSetAccessible {

	public static void change(String str) throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		// 方式1
		// str = "welcome";

		// 方式2
		Class<?> clazz = str.getClass(); // 得到类名

		Field fieldValue = clazz.getDeclaredField("value"); // 得到值属性名
		fieldValue.setAccessible(true); // 设置属性有访问权限，避免modifiers
										// "private final"异常
		Object obj = fieldValue.get(str);
		char[] charValue = (char[]) obj;
		charValue = new char[4]; // 改变值为aaa
		for (int i = 0; i < charValue.length; i++) {
			charValue[i] = 'a';
		}

		Field fieldCount = clazz.getDeclaredField("count"); // 得到长度属性名
		fieldCount.setAccessible(true);
		fieldCount.set(str, charValue.length);

		fieldValue.set(str, charValue); // 重新设置值
	}

	public static void main(String[] args) throws Exception {
		String str = "123";
		change(str);
		System.out.println("改变后的字符为" + str);
	}
}
