package com.yeahwell.demo.j2se.core.reflection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * 源文件编译后的字节码,普通常规的字节码是存放在磁盘的.class文件。
 * 
 * @author 杨立昆
 * 
 */
public class MemoryClassObject extends SimpleJavaFileObject {

	/**
	 * 存放编译器编译后的字节码,后续可以用类加载器加载这个字节码生成类。
	 */
	protected final ByteArrayOutputStream bos = new ByteArrayOutputStream();

	/**
	 * Registers the compiled class object under URI containing the class full
	 * name
	 * 
	 * @param name
	 *            编译后的全类名(包名+类名)
	 * @param kind
	 *            文件抽象，这里应该使用JavaFileObject.Kind.CLASS
	 */
	public MemoryClassObject(String name, Kind kind) {
		super(
				URI.create("string:///" + name.replace('.', '/')
						+ kind.extension), kind);
	}

	/**
	 * Will be used by our file manager to get the byte code that can be put
	 * into memory to instantiate our class
	 * 
	 * @return compiled byte code
	 */
	public byte[] getBytes() {
		return bos.toByteArray();
	}

	/**
	 * Will provide the compiler with an output stream that leads to our byte
	 * array. This way the compiler will write everything into the byte array
	 * that we will instantiate later
	 */
	@Override
	public OutputStream openOutputStream() throws IOException {
		return bos;
	}

}
