package com.yeahwell.acm.visualgo.sorting;


public class SelectionSort {

	public int[] selectionSort(int[] array){
		
		int compareCount = 0;
		for(int i = 0; i < array.length - 1; i++){
			
			//假设最小
			int tempIndex = i;
			
			//寻找更小
			for(int j = i + 1; j < array.length; j++){
				if(array[tempIndex] > array[j]){
					tempIndex = j;
				}
				compareCount++;
			}
			
			//更换位置
			int tempData = array[tempIndex];
			array[tempIndex] = array[i];
			array[i] = tempData;
		}
		
		System.out.println("比较次数=" + compareCount);
		return array;
	}
	
}
