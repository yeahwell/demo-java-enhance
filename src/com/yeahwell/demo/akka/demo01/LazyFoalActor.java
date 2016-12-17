package com.yeahwell.demo.akka.demo01;

import akka.actor.UntypedActor;

/**
 * @author cyd
 * @date 2015-3-24
 */

public class LazyFoalActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		Thread.sleep(5000);
		System.out.println("LazyFoalActor接到消息: " + message);
	}
}