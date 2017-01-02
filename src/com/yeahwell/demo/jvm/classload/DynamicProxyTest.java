package com.yeahwell.demo.jvm.classload;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

	interface IHello{
		void sayHello();
	}
	
	static class Hello implements IHello{

		@Override
		public void sayHello() {
			System.out.println("Hello world");
		}
		
	}
	
	static class DynamicProxy implements InvocationHandler{

		Object originalObj;
		
		Object bind(Object originalObj){
			this.originalObj = originalObj;
			return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("welcome");
			return method.invoke(originalObj, args);
		}
		
	}
	
	public static void main(String[] args) {
		//加入这句代码后再次运行程序，磁盘中将会产生一个名为$Proxy0.class的代理类Class文件
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		IHello hello = (IHello) new DynamicProxy().bind(new Hello());
		hello.sayHello();
	}
	
}
