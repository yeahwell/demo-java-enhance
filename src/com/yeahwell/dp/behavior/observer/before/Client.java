package com.yeahwell.dp.behavior.observer.before;

public class Client {

	public static void main(String[] args) {
		
		Publisher publisher = new Publisher();
		
		publisher.setCustomer(new Customer());
		
		publisher.setTv(new TV());
		
		publisher.setNews("新连锁店开张了");
		publisher.setNews("打折促销啦了");
	}
}
