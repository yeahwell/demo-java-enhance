package com.yeahwell.dp.structure.adapter1;

//适配器类，继承了被适配类，同时实现标准接口  
class Adapter extends Adaptee implements Target {
	public void request() {
		super.specificRequest();
	}

	// 测试类public class Client {
	public static void main(String[] args) {
		// 使用普通功能类
		Target concreteTarget = new ConcreteTarget();
		concreteTarget.request();

		// 使用特殊功能类，即适配类
		Target adapter = new Adapter();
		adapter.request();
	}
}
