package com.yeahwell.demo.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * 运行akka.Main，设置参数为com.yeahwell.demo.akka.HelloWorld
 * @author yeahwell
 *
 */
public class HelloWorld extends UntypedActor{
	
	@Override
	public void preStart(){
		//create the greeter actor
		final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		long beforeTime = System.currentTimeMillis();
		//tell it to perform the greeting
		greeter.tell(Greeter.Msg.GREET, getSelf());
		long afterTime = System.currentTimeMillis();
		System.out.println("执行tell，所用时间=" + (afterTime - beforeTime) + "毫秒");
	}
	
	@Override
	public void onReceive(Object msg){
		if(msg == Greeter.Msg.DONE){
			getContext().stop(getSelf());
		}else{
			unhandled(msg);
		}
	}


	
}
