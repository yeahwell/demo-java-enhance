package com.yeahwell.demo.j2se.core.reflection.bytecode;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * 动态编译Java源文件
 * 
 * @author yeahwell 典型的场景是很多 算法竞赛的在线评测系统（如PKU JudgeOnline），允许用户上传Java代码，由系统在后台编译、运行并进行判定。
 */
public class TestCompiler {

	public static void main(String[] args) throws URISyntaxException {
		//目标源代码
		String source = "public class Main { public static void main(String[] args) {System.out.println(\"Hello World!\");} }";
		//Java编译器类
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//文件管理器
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		//Java类对象
		StringSourceJavaObject sourceObject = new
				TestCompiler.StringSourceJavaObject("Main", source);
		
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(sourceObject);
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null, fileObjects);
		boolean result = task.call();  
		if(result){
			System.out.println("编译成功");
		}
	
	}

	static class StringSourceJavaObject extends SimpleJavaFileObject {

		private String content = null;

		public StringSourceJavaObject(String name, String content)
				throws URISyntaxException {
			super(URI.create("string:///" + name.replace('.', '/')
					+ Kind.SOURCE.extension), Kind.SOURCE);
			this.content = content;
		}

		public CharSequence getCharContent(boolean ignoreEncodingErrors)
				throws IOException {
			return content;
		}
	}
}
