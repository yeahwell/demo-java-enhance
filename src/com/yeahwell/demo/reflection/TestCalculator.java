package com.yeahwell.demo.reflection;

import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.yeahwell.demo.reflection.TestCompiler.StringSourceJavaObject;

public class TestCalculator {

	
	public static double calculate(String expr) throws CalculationException, URISyntaxException {
		String className = "CalculatorMain";
		String methodName = "calculate";
		String source = "public class " + className
				+ " { public static double " + methodName + "() { return "
				+ expr + "; } }";
		// 省略动态编译Java源代码的相关代码，参见上一节
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
		if (result) {
			//得到类加载器
			ClassLoader loader = TestCalculator.class.getClassLoader();
			try {
				Class<?> clazz = loader.loadClass(className);
				Method method = clazz.getMethod(methodName, new Class<?>[] {});
				Object value = method.invoke(null, new Object[] {});
				return (Double) value;
			} catch (Exception e) {
				throw new CalculationException("内部错误。");
			}
		} else {
			throw new CalculationException("错误的表达式。");
		}
	}
	
	static class CalculationException extends RuntimeException{
		
		private String name;
		
		public CalculationException(String name){
			this.name = name;
		}
	}
}
