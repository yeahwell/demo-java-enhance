package com.yeahwell.demo.j2se.core.reflection;

import java.io.IOException;
import java.security.SecureClassLoader;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;

/**
 * 
 * 让告诉编译器将字节码输出我们自己的内存对象MemoryClassObject
 * 
 * @author 杨立昆
 * 
 */
public class ClassFileManager extends
		ForwardingJavaFileManager<StandardJavaFileManager> {

	/**
	 * 存储编译后的字节码
	 */
	private MemoryClassObject jclassObject;

	/**
	 * 用标准的java文件管理器来初始化
	 * 
	 * @param standardManger
	 */
	public ClassFileManager(StandardJavaFileManager standardManager) {
		super(standardManager);
	}

	/**
	 * 调用这个获取类加载器,进而加载动态编译后的字节码类
	 */
	@Override
	public ClassLoader getClassLoader(Location location) {
		return new SecureClassLoader() {
			@Override
			protected Class<?> findClass(String name)
					throws ClassNotFoundException {
				byte[] b = jclassObject.getBytes();
				return super.defineClass(name, jclassObject.getBytes(), 0,
						b.length);
			}
		};
	}

	/**
	 * 告诉编译器将编译后的字节码放到内存对象中（MemoryClassObject）
	 */
	@Override
	public JavaFileObject getJavaFileForOutput(Location location,
			String className, Kind kind, FileObject sibling) throws IOException {
		jclassObject = new MemoryClassObject(className, kind);
		return jclassObject;
	}
}