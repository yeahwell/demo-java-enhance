package com.yeahwell.dp.behavior.observer.before;

/**
 *连锁店的消息发布者
 * */
public class Publisher {

	private String news;
	
	private Customer customer;
	
	private TV tv;

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
		this.sendNews();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TV getTv() {
		return tv;
	}

	public void setTv(TV tv) {
		this.tv = tv;
	}
	
	/**
	 * 发送消息
	 */
	public void sendNews(){
		customer.read(news);
		tv.play(news);
	}
	
}
