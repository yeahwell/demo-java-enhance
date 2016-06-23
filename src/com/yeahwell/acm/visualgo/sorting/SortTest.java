package com.yeahwell.acm.visualgo.sorting;

import java.util.Arrays;

import org.junit.Test;

public class SortTest {

	@Test
	public void testSort(){
		
		int[] array1 = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
		System.out.println("冒泡排序前" + Arrays.toString(array1));
		new BubbleSort().bubbleSort(array1);
		System.out.println("冒泡排序后" + Arrays.toString(array1));
		
		int[] bubbleSortArray2 = {18, 13, 16, 14, 17, 15};
		System.out.println("冒泡排序前" + Arrays.toString(bubbleSortArray2));
		new BubbleSort().bubbleSort2(bubbleSortArray2);
		System.out.println("冒泡排序后" + Arrays.toString(bubbleSortArray2));
		
		int[] bubbleSortArray3 = {4, 12, 3, 22, 28};
		System.out.println("冒泡排序前" + Arrays.toString(bubbleSortArray3));
		new BubbleSort().bubbleSort2(bubbleSortArray3);
		System.out.println("冒泡排序后" + Arrays.toString(bubbleSortArray3));
		
		int[] array2 = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
		System.out.println("选择排序前" + Arrays.toString(array2));
		new SelectionSort().selectionSort(array2);
		System.out.println("选择排序后" + Arrays.toString(array2));
		
		int[] selectSortArray2 = {16, 13, 14, 15, 17, 18};
		System.out.println("选择排序前" + Arrays.toString(selectSortArray2));
		new SelectionSort().selectionSort(selectSortArray2);
		System.out.println("选择排序后" + Arrays.toString(selectSortArray2));
		
		int[] insertSortArray = {21, 5, 2, 19, 16, 15};
		System.out.println("插入排序前" + Arrays.toString(insertSortArray));
		new InsertSort().insertSort(insertSortArray);
		System.out.println("插入排序后" + Arrays.toString(insertSortArray));
		
		int[] array3 = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
		System.out.println("基数排序前" + Arrays.toString(array3));
		new RadixSort().radixSort(array3, 2);
		System.out.println("基数排序后" + Arrays.toString(array3));
		

	}
	
}
