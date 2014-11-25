package com.yeahwell.demo.reflection.bytecode;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * 待编译的源代码，一般常规我们的源码是存放在磁盘的.java文件。
 * 
 * @author 杨立昆
 */
public class StringSourceJavaObject extends SimpleJavaFileObject {

	/**
	 * 待编译的源代码
	 */
	private String sourceCode;

	/**
	 * 
	 * @param className
	 *            源码代码里public类的全类名(包名+类名)
	 * @param sourceCode
	 *            待编译的源代码
	 */
	protected StringSourceJavaObject(String className, String sourceCode) {
		super(URI.create("string:///" + className.replace('.', '/')
				+ Kind.SOURCE.extension), Kind.SOURCE);
		this.sourceCode = sourceCode;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors)
			throws IOException {
		return sourceCode;
	}

}
