package com.yeahwell.dp.behavior.observer4;

/**
 * 基于事件/监听器的例子
 * @author yeahwell
 *
 */
public class JDKEventExample {

	public static void main(String[] args) {
		
		/**
		 * 信息主体
		 */
		MyObject object = new MyObject();
		
		/**
		 * 增加监听器
		 */
		object.addListener(new MyListener());
		/**
		 * 匿名监听器
		 */
		object.addListener(new IMyEventListener() {
			
			@Override
			public void doListener(MyEvent myEvent) {
				System.out.println("匿名类： " + myEvent.getMsg());
			}
		});
		
		object.doEvent();
	}
}
