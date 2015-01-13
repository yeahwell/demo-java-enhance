package com.yeahwell.demo.akka;

import akka.actor.ActorRef;
//import static akka.actor.Actors.remote;

public class MockClient implements Runnable {

	private int seq;

	private String serviceName;

	public MockClient(int seq, String serviceName) {
		this.seq = seq;
		this.serviceName = serviceName;
	}

	@Override
	public void run() {
		 //ActorRef actor = remote().actorFor(serviceName, "127.0.0.1", 9999); 
	     String str = "Hello--" + seq;
	     System.out.println("请求-----${str}");
	    // Object res = actor.sendRequestReply(str);
	     System.out.println("返回-----${res}");
	}

	public static void main(String[] args) { 
	        for (int i = 0; i < 5; i++) { 
	            Thread thread = new Thread(new MockClient(i, "hello-service")); 
	            thread.start();        //同时启动5个客户端请求Master 
	        } 
	    }
	
}