package com.yeahwell.dp.behavior.iterator10;

public interface Iterator {

	/**
	 * 前移
	 * @return
	 */
	public Object previous();
	
	/**
	 * 后移
	 * @return
	 */
	public Object next();
	
	public boolean hasNext();
	
	/**
	 * 取得第一个元素
	 * @return
	 */
	public Object first();
	
	
}
