package com.yeahwell.dp.behavior.observer2;

/**
 * 新闻发布类
 * @author yeahwell
 *
 */
public class NewsPublisher extends Subject{

	private String news;
	
	public void setNews(String news){
		/**
		 * 消息改变时通知
		 */
		if(news != null && !news.equals(this.news)){
			this.news = news;
			/**
			 * 通知监听器
			 */
			this.notify(news);
		}
	}
}
