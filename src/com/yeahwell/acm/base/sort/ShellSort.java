package com.yeahwell.acm.base.sort;

public class ShellSort {

	
	public void shellSort(int[] array){
		//取增量
		int step = array.length / 2;
		while(step >= 1){
			//无序序列
			for(int i = step; i < array.length; i++){
				int temp = array[i];
				int j;
				//有序序列
				for(j = i - step; j >= 0 && temp < array[j]; j = j - step){
					array[j + step] = array[j];
				}
				array[j + step] = temp;
			}
			step = step / 2;
		}
	}
}
