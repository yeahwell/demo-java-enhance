package com.yeahwell.demo.j2se.core.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class DynamicCompilerMain {

	public static void main(String[] args) throws Exception {

		// 类全名(包名+类名)
		String fullName = "name.ylk.Test";

		// 待编译的源码
		StringBuilder src = new StringBuilder();
		src.append("package name.ylk;public class Test {\n");
		src.append("    public String toString() {\n");
		src.append("        return \"Hello, I am www.ylk.name \"; ");
		src.append("    }\n");
		src.append("}\n");

		// 获取一个编译器,然后生成一个文件管理器(告诉编译器将编译后的字节码放到内存（MemoryClassObject）)
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		JavaFileManager fileManager = new ClassFileManager(
				compiler.getStandardFileManager(null, null, null));

		// 需要动态编译的代码
		List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
		jfiles.add(new StringSourceJavaObject(fullName, src.toString()));

		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

		// 使用fileManager和jfiles给编译分配一个任务,调用call()来让编译器运行。
		Boolean compilerResult = compiler.getTask(null, fileManager,
				diagnostics, null, null, jfiles).call();

		if (compilerResult) {
			System.out.println("编译成功");
			// 创建一个动态编译的磊实例,调用toString()方法
			Class<?> loadClass = fileManager.getClassLoader(null).loadClass(
					fullName);
			Object instance = loadClass.newInstance();
			System.out.println("调用toString():" + instance);
			// 下面是反射的代码
			Method method = loadClass.getMethod("toString");
			System.out.println("反射调用:" + method.invoke(instance));
		} else {
			System.err.println("编译出错");
			for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics
					.getDiagnostics()) {
				System.err.format("Error on line %d in %s\n",
						diagnostic.getLineNumber(), diagnostic);
			}

		}
	}
}