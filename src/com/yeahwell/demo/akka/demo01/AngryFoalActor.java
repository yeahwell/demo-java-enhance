package com.yeahwell.demo.akka.demo01;

import akka.actor.UntypedActor;

/**
 * @author cyd
 * @date 2015-3-24
 */
public class AngryFoalActor extends UntypedActor {

	public void onReceive(Object message) throws Exception {
		System.out.println("AngryFoalActor接收消息 " + message);
		Thread.sleep(5000);
		getSender().tell("你好，我是AngryFoalActor", getSelf());
	}
}

