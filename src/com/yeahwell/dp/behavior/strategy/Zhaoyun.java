package com.yeahwell.dp.behavior.strategy;

/**
 * 场景如下，刘备要到江东娶老婆了，走之前诸葛亮给赵云三个锦囊妙计，说是按天机拆开能解决棘手问题。场景中出现三个要素：三个妙计（具体策略类）、一个锦囊（环境类）、赵云（调用者）。
 * @author yanjiawei
 *
 */
public class Zhaoyun {

	public static void main(String[] args) {
		
		//以下就是策略模式，多种不同解决方案动态切换，起到改变对象行为的效果
		Context context;

		System.out.println("----------刚到吴国使用第一个锦囊---------------");
		context = new Context(new BackDoor());
		context.operate();
		System.out.println("\n");

		System.out.println("----------刘备乐不思蜀使用第二个锦囊---------------");
		context.setStrategy(new GivenGreenLight());
		context.operate();
		System.out.println("\n");

		System.out.println("----------孙权的追兵来了，使用第三个锦囊---------------");
		context.setStrategy(new BlackEnemy());
		context.operate();
		System.out.println("\n");
	}
	
}