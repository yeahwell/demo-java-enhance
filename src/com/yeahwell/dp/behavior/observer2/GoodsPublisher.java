package com.yeahwell.dp.behavior.observer2;

/**
 * 商品发布类
 * @author yeahwell
 *
 */
public class GoodsPublisher extends Subject{

	private String news;
	
	public void setNews(String news){
		if(news != null && !news.equals(this.news)){
			/**
			 * 通知监听器
			 */
			this.notify(news);
		}
	}
}
