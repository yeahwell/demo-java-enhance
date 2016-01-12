package com.yeahwell.acm.base.sort;


public class BubbleSort {
	
	/**
	 * 冒泡排序
	 * @param array
	 * @return
	 */
	public int[] bubbleSort(int[] array){
		int temp ; 
		for(int i = 0; i < array.length; i++){
			for(int j = array.length - 1; j > i; j--){
				if(array[j - 1] > array[j]){
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	
}
