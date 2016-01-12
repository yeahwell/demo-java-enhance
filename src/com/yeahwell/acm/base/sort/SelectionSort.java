package com.yeahwell.acm.base.sort;

public class SelectionSort {

	public int[] sectionSort(int[] array){
		//要遍历的次数
		for(int i = 0; i < array.length - 1; i++){
			//假设tempIndex的小标的值最小
			int tempIndex = i;
			for(int j = i + 1; j < array.length; j++){
				//如果tempIndex下标的值大于j下标的值,则记录较小值下标j
				if(array[tempIndex] > array[j]){
					tempIndex = j;
				}
			}
			
			//最后将假想最小值跟真的最小值进行交换
			int tempData = array[tempIndex];
			array[tempIndex] = array[i];
			array[i] = tempData;
		}
		return array;
	}
}
