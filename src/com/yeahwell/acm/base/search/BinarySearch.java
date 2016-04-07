package com.yeahwell.acm.base.search;

public class BinarySearch {

	public int binarySearch(int[] array, int key){
		//最低线
		int low = 0;
		//最高线
		int high = array.length - 1;
		while(low <= high){
			//取中间值
			int middle = (low + high) / 2;
			if(array[middle] == key){
				return middle;
			}else if(array[middle] > key){
				//下降一半
				high = middle - 1;
			}else {
				//上升一半
				low = middle + 1;
			}
		}
		return -1;
	}
}
