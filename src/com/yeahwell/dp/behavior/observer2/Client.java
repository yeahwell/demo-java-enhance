package com.yeahwell.dp.behavior.observer2;

public class Client {

	public static void main(String[] args) {
		/**
		 * 初始化主题对象
		 */
		NewsPublisher newsPublisher = new NewsPublisher();
		GoodsPublisher goodsPublisher = new GoodsPublisher();
		
		/**
		 * 初始化监听器
		 */
		IObserver observerA = new Customer();
		IObserver observerB = new TV();
		
		/**
		 * 注册监听器，注册两个注册的不同组合
		 */
		newsPublisher.addObserver(observerA);
		
		goodsPublisher.addObserver(observerA);
		goodsPublisher.addObserver(observerB);
		
		/**
		 * 触动改变
		 */
		newsPublisher.setNews("新闻： 新店开张");
		goodsPublisher.setNews("商品： 新货上架");
	}
}
