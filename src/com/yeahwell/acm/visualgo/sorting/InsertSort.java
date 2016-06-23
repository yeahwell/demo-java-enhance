package com.yeahwell.acm.visualgo.sorting;

import java.util.Arrays;


public class InsertSort {

	public int[] insertSort(int[] array){
		
		for(int i = 1; i < array.length; i++){
			
			int temp = array[i];
			int j;
			for(j = i - 1; j >= 0 && temp < array[j]; j--){
				array[j + 1] = array[j];
			}
			array[j + 1] = temp;
			
			System.out.println("第" + i + "次 array=" + Arrays.toString(array));
		}
		
		return array;
	}
	
	public int[] insertSort2(int[] array){
		for(int i = 1; i < array.length; i++){
			int temp = array[i];
			int j = i;
			while(j > 0){
				if(array[j - 1] > temp){
					array[j] = array[j - 1];
				}else{
					break;
				}
				j--;
			}
			array[j] = temp;
		}
		return array;
	}
	
}
