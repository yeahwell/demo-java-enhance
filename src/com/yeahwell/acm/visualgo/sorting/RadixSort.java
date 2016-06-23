package com.yeahwell.acm.visualgo.sorting;

public class RadixSort {

	public int[] radixSort(int[] array, int d) {
		int k = 0, n = 1, m = 1; //控制键值排序依据在哪一位
		int[][] temp = new int[10][array.length]; //数组的第一维表示可能的余数0-9
		int[] order = new int[10]; //数组order[i]用来表示该位是i的数个个数
		while(m <= d){
			for(int i = 0; i < array.length; i++){
				int lsd = (array[i] / n) % 10;
				temp[lsd][order[lsd]] = array[i];
				order[lsd]++;
			}
			for(int i = 0; i < 10; i++){
				if(order[i] != 0){
					for(int j = 0; j < order[i]; j++){
						array[k] = temp[i][j];
						k++;
					}
				}
				order[i] = 0;
			}
			n *= 10;
			k = 0;
			m++;
		}
		return array;
	}
	

}
