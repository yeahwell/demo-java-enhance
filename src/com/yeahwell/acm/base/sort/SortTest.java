package com.yeahwell.acm.base.sort;

import java.util.Arrays;

import org.junit.Test;

public class SortTest {
	
	public static void printArray(int[] array){
		int maxIndex = array.length;
		if(array.length < 10){
			maxIndex = array.length;
		}else{
			maxIndex = 10;
		}
		
		for(int i = array.length - 1; i >= array.length - 1 - maxIndex && i >= 0; i--){
			System.out.print(array[i] + " ");
		}
		
		for(int i = 0; i < maxIndex; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
//	@Test
	public void test_bigDataSort(){
		int size = 20000;
		for(int i = 1; i <= 5; i++){
			int[] array = new int[size];
			//插入2k歌随机数到数组中
			for(int j = 0; j < size; j++){
				array[j] = (int)(Math.random() * 100000);
			}
			System.out.println("=========第" + i + "次比较==========");
			
			int[] arrayForBubble = new int[size];
			System.arraycopy(array, 0, arrayForBubble, 0, array.length);
			
			int[] arrayForJavaQuick = new int[size];
			System.arraycopy(array, 0, arrayForJavaQuick, 0, array.length);
			
			int[] arrayForQuick = new int[size];
			System.arraycopy(array, 0, arrayForQuick, 0, array.length);
			
			int[] arrayForSelection = new int[size];
			System.arraycopy(array, 0, arrayForSelection, 0, array.length);
			
			int[] arrayForHeap = new int[size];
			System.arraycopy(array, 0, arrayForHeap, 0, array.length);
			
			int[] arrayForInsert = new int[size];
			System.arraycopy(array, 0, arrayForInsert, 0, array.length);
			
			int[] arrayForShell = new int[size];
			System.arraycopy(array, 0, arrayForShell, 0, array.length);
			
			int[] arrayForMerge = new int[size];
			System.arraycopy(array, 0, arrayForMerge, 0, array.length);
			
			long startTime = System.currentTimeMillis();
			new BubbleSort().bubbleSort(arrayForBubble);
			long endTime = System.currentTimeMillis();
			System.out.println("冒泡排序耗时为         " + (endTime - startTime));
			//printArray(arrayForBubble);
			
			startTime = System.currentTimeMillis();
			Arrays.sort(arrayForJavaQuick);
			endTime = System.currentTimeMillis();
			System.out.println("JVM实现的快速排序耗时为 " + (endTime - startTime));
			//printArray(arrayForJavaQuick);
			
			startTime = System.currentTimeMillis();
			new QuickSort().quickSort(arrayForQuick, 0, size - 1);
			endTime = System.currentTimeMillis();
			System.out.println("自实现的快速排序耗时为  " + (endTime - startTime));
			//printArray(arrayForQuick);
			
			startTime = System.currentTimeMillis();
			new SelectionSort().sectionSort(arrayForSelection);
			endTime = System.currentTimeMillis();
			System.out.println("选择排序耗时为         " + (endTime - startTime));
			//printArray(arrayForSelection);
			
			startTime = System.currentTimeMillis();
			new HeapSort().heapSort(arrayForHeap);
			endTime = System.currentTimeMillis();
			System.out.println("堆排序耗时为           " + (endTime - startTime));
			//printArray(arrayForHeap);
			
			startTime = System.currentTimeMillis();
			new InsertSort().insertSort(arrayForInsert);
			endTime = System.currentTimeMillis();
			System.out.println("插入排序耗时为         " + (endTime - startTime));
//			printArray(arrayForInsert);
			
			startTime = System.currentTimeMillis();
			new ShellSort().shellSort(arrayForShell);
			endTime = System.currentTimeMillis();
			System.out.println("希尔排序耗时为         " + (endTime - startTime));
//			printArray(arrayForShell);
			
			startTime = System.currentTimeMillis();
			new MergeSort().mergeSort(arrayForMerge, new int[arrayForMerge.length], 0, arrayForMerge.length - 1);
			endTime = System.currentTimeMillis();
			System.out.println("归并排序耗时为         " + (endTime - startTime));
//			printArray(arrayForMerge);
			
		}
	}
	
	@Test
	public void test_countSort(){
		 int test1[] = {2, 6, 4, 3, 2, 3, 4, 6, 3, 4, 3, 5, 2, 6};
		 int test2[] = {5, 6, 7, 8, 5};
		 int test3[] = {8, 1, 2, 3, 3, 4};
		 int test4[] = {3, 4, 3, 2, 1};
		 int test5[] = {0, 5, 3, 7, 2, 3};
		 new CountSort().countSort(test1);
		 new CountSort().countSort(test2);
		 new CountSort().countSort(test3);
		 new CountSort().countSort(test4);
		 new CountSort().countSort(test5);
	}
}
