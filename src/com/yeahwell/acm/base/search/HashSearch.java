package com.yeahwell.acm.base.search;

public class HashSearch {

	public int hashSearch(int[] hash, int hashLength, int key){
		
		//哈希函数
		int hashAddress = key % hashLength;
		
		//指定hashAddress对一个值存在但不是关键值，则用开放寻址法解决
		while (hash[hashAddress] != 0 && hash[hashAddress] != key){
			hashAddress = (++hashAddress) % hashLength;
		}
		
		//查找到了开放单元，表示查找失败
		if(hash[hashAddress] == 0)
			return -1;
		return hashAddress;
	}
	
	public void insertHash(int[] hash, int hashLength, int data){
		//哈希函数
		int hashAddress = data % hashLength;
		//如果key存在，则说明已经被别人占用，此时必须解决冲突
		while (hash[hashAddress] != 0){
			//用开放寻址法找到
			hashAddress = (++hashAddress) % hashLength;
		}
		
		//将data存入字典中
		hash[hashAddress] = data;
	}
	
	public static void main(String[] args) {
		//"除法取余法"
		int hashLength = 13;
		//原数组
		int[] orgArray = {13, 29, 27, 28, 26, 30, 38};
		int[] hash = new int[hashLength];
		HashSearch search = new HashSearch();
		//创建hash
		for(int i = 0; i < orgArray.length; i++){
			search.insertHash(hash, hashLength, orgArray[i]);
		}
		
		System.out.println("hash数组为" + hash);
		for(int i : hash){
			System.out.print(i + " ");
		}
		System.out.println();
		
		int key = 32;
		int index = search.hashSearch(hash, hashLength, key);
		System.out.println("查找位置" + index);
	}
	
	
}
