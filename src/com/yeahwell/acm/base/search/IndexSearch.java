package com.yeahwell.acm.base.search;


public class IndexSearch {

	private IndexItem[] indexItemArray;

	private int[] tableArray;

	public IndexSearch(IndexItem[] indexItemArray, int[] tableArray) {
		this.indexItemArray = indexItemArray;
		this.tableArray = tableArray;
	}

	public int indexSearch(int key) {
		IndexItem item = null;
		// 建立索引规则
		int index = key / 100;
		// 首先去索引找
		for (int i = 0; i < indexItemArray.length; i++) {
			if (indexItemArray[i].index == index) {
				item = indexItemArray[i];
				break;
			}
		}
		// 如果item为null，则说明在索引中查找失败
		if (null == item) {
			return -1;
		}
		for (int i = item.start; i < item.start + item.length; i++) {
			if (tableArray[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	public int insert(int key){
		IndexItem item = null;
		//建立索引规则
		int index = key / 100;
		int i = 0;
		for(i = 0; i < indexItemArray.length; i++){
			//获取到了索引
			if(indexItemArray[i].index == index){
				item = indexItemArray[i];
				break;
			}
			
		}
		if(null == item){
			return -1;
		}
		//更新主表
		tableArray[item.start + item.length] = key;
		//更新索引表
		indexItemArray[i].length++;
		return 1;
	}

	public static void main(String[] args) {
		// 学生索引表
		IndexItem[] indexItemArray = { new IndexItem(1, 0, 5),
				new IndexItem(2, 10, 4), new IndexItem(3, 20, 3), };
		// 学生主表
		int[] tableArray = { 101, 102, 103, 104, 105, 0, 0, 0, 0, 0, 201, 202,
				203, 204, 0, 0, 0, 0, 0, 0, 301, 302, 303, 0, 0, 0, 0, 0, 0, 0 };

		IndexSearch search = new IndexSearch(indexItemArray, tableArray);

		int value = 205;
		// 将205插入集合中，过索引
		int index = search.insert(value);
		// 如果插入成功，获取205元素所在的位置
		if (index == 1) {
			System.out.println(search.indexSearch(value));
		}
	}

}
