package com.yeahwell.acm.base.sort;

public class CountSort {
	
	public void printArray(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public int maximum(int[] array){
		int max = 0;
		for(int i = 0; i < array.length; i++){
			if(array[i] > max){
				max = array[i];
			}
		}
		return max;
	}
	
	public int[] orgArray2countArray(int[] orgArray){
		int max = maximum(orgArray);
		int[] countArray = new int[max + 1];
		for(int i = 0; i < orgArray.length; i++){
			countArray[orgArray[i]] ++;
			System.out.print("第" + i + "步countArray=");
			printArray(countArray);
		}
		return countArray;
	}
	
	public int[] countArray2targeArray(int orgArrayLength, int[] countArray){
		int[] targetArray = new int[orgArrayLength];
		int targetArrayCursor = 0;
		int num = 0;
		for(int i = 0; i < countArray.length; i++){
			num = countArray[i];
			while(num > 0){
				targetArray[targetArrayCursor++] = i;
				num--;
				System.out.print("第" + i + "步targetArray=");
				printArray(targetArray);
			}
		}
		return targetArray;
	}

	public void countSort(int[] array){
		int orgArrayLength = array.length;
		int[] countArray = orgArray2countArray(array);
		int[] targetArray = countArray2targeArray(orgArrayLength, countArray);
		printArray(targetArray);
	}
	
}
