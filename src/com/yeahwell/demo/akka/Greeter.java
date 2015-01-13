package com.yeahwell.demo.akka;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

	public static enum Msg {
		GREET, DONE;
	}

	@Override
	public void onReceive(Object msg){
		if(msg == Msg.GREET){
			
			long beforeTime = System.currentTimeMillis();
			System.out.println("开始记录日志---" + beforeTime);
			//先睡5秒
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			System.out.println("Hello world");
			long afterTime = System.currentTimeMillis();
			System.out.println("结束记录日志---" + afterTime);
			System.out.println("记录日志耗时---" + (afterTime - beforeTime) + "毫秒");
			
			getSender().tell(Msg.DONE, getSelf());
		}else{
			unhandled(msg);
		}
	}

}
