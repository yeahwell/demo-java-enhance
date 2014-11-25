package com.yeahwell.dp.behavior.observer4;

import java.util.EventListener;

/**
 * 监听器接口
 * @author yeahwell
 *
 */
public interface IMyEventListener extends EventListener{

	public void doListener(MyEvent myEvent);
	
}
