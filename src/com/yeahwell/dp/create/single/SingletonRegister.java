package com.yeahwell.dp.create.single;

import java.util.HashMap;
import java.util.Map;

//类似Spring里面的方法，将类名注册，下次从里面直接获取。
public class SingletonRegister {

	private static Map<String, SingletonRegister> map = new HashMap<String, SingletonRegister>();
	static {
		SingletonRegister single = new SingletonRegister();
		map.put(single.getClass().getName(), single);
	}

	// 保护的默认构造子
	protected SingletonRegister() {
	}

	// 静态工厂方法,返还此类惟一的实例
	public static SingletonRegister getInstance(String name) {
		if (name == null) {
			name = SingletonRegister.class.getName();
			System.out.println("name == null" + "--->name=" + name);
		}
		if (map.get(name) == null) {
			try {
				map.put(name, (SingletonRegister) Class.forName(name)
						.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return map.get(name);
	}

	// 一个示意性的商业方法
	public String about() {
		return "Hello, I am RegSingleton.";
	}

	public static void main(String[] args) {
		SingletonRegister single3 = SingletonRegister.getInstance(null);
		System.out.println(single3.about());
	}

}
