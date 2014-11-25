package com.yeahwell.dp.behavior.observer4;

/**
 * 监听器
 * @author yeahwell
 *
 */
public class MyListener implements IMyEventListener{

	@Override
	public void doListener(MyEvent myEvent) {
		System.out.println("MyListener接收新事件" + myEvent.getMsg());
	}

}
