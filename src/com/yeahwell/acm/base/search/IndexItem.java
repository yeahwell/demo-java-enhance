package com.yeahwell.acm.base.search;

public class IndexItem {

	public IndexItem(int index, int start, int length){
		this.index = index;
		this.start = start;
		this.length = length;
	}
	
	//对应主表的值
	public int index;
	//主表记录区间的开始位置
	public int start;
	//主表记录区间段的长度
	public int length;
}
