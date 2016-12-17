package com.yeahwell.demo.akka.demo01;

import akka.actor.ActorRef;

/**
 * @author cyd
 * @date 2015-3-25
 */
public class Main {
	
	public static void main(String[] args) {
		ActorSystemTools.start();
		
		ActorRef angryFoal = ActorSystemTools.actorOf(AngryFoalActor.class);
		ActorRef lazyFoal = ActorSystemTools.actorOf(LazyFoalActor.class);
		
		angryFoal.tell("fuck you", lazyFoal);
		
		//ActorSystemTools.shutdown();
	}

}

