package com.yeahwell.acm.visualgo.sorting;

import java.util.Arrays;

public class BubbleSort {

	public int[] bubbleSort(int[] array) {
		int length = array.length;
		boolean swapped = true;
		int compareCount = 0;
		do {
			swapped = false;
			for (int i = 0; i < length - 1; i++) {
				System.out.println("compare(" + array[i] + "," + array[i + 1] + ")");
				if (array[i] > array[i + 1]) {
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					swapped = true;
				}
				compareCount++;
				System.out.println("第" + i + "趟 array=" + Arrays.toString(array));
			}
		} while (swapped);
		System.out.println("冒泡排序比较次数=" + compareCount);
		return array;
	}
	
	public int[] bubbleSort2(int[] array) {
		int length = array.length;
		int compareCount = 0;
		for(int j = 0; j < length - 1; j++){
			for(int i = 0; i < length - j - 1; i++){
				System.out.println("compare(" + array[i] + "," + array[i + 1] + ")");
				if(array[i] > array[i + 1]){
					int temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
				}
				compareCount++;
			}
			System.out.println("第" + j + "趟 array=" + Arrays.toString(array));
		}
		System.out.println("冒泡排序比较次数=" + compareCount);
		return array;
	}

}
