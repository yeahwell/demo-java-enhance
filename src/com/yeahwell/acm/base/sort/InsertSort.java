package com.yeahwell.acm.base.sort;


public class InsertSort {
	
	public void insertSort(int[] array){
		//无序序列
		for(int i = 1; i < array.length; i++){
			int temp = array[i];
			int j;
			//有序序列
			for(j = i - 1; j >= 0 && temp < array[j]; j--){
				array[j + 1] = array[j];
			}
			array[j + 1] = temp;
		}
	}
	
}
