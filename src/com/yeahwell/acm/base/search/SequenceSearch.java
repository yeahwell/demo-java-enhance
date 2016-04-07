package com.yeahwell.acm.base.search;

public class SequenceSearch {

	public int sequenceSearch(int[] array, int key){
		for(int i = 0; i < array.length; i++){
			if(key == array[i]){
				return i;
			}
		}
		return -1;
	}
	
}
