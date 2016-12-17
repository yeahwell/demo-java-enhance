package com.yeahwell.acm.base.sort;

public class MergeSort {
	
	/**
	 * 数组的划分
	 * @param array 待排序数组
	 * @param temparray 临时存放数组
	 * @param left 序列段的开始位置
	 * @param right 序列段的结束位置
	 */
	public void mergeSort(int[] array, int[] temparray, int left, int right){
		if(left < right){
			//取分割位置
			int middle = (left + right) / 2;
			//递归划分数组左序列
			mergeSort(array, temparray, left, middle);
			//递归划分数组右序列
			mergeSort(array, temparray, middle + 1, right);
			//数组合并操作
			merge(array, temparray, left, middle + 1, right);
		}
	}

	/**
	 * 数组的两两合并操作
	 * @param array 待排序数组
	 * @param temparray 临时数组
	 * @param left 第一个区间段的开始位置
	 * @param middle 第二个区间段的开始位置
	 * @param right 第二个区间段的结束位置
	 */
	public void merge(int[] array, int[] temparray, int left, int middle, int right){
		//左指针尾
		int leftEnd = middle - 1;
		//右指针头
		int rightStart = middle;
		//临时数组的下标
		int tempIndex = left;
		//数组合并后的length长度
		int tempLength = right - left + 1;
		//先循环两个区间段都没有结束的情况
		while((left <= leftEnd) && (rightStart <= right)){
			//如果发现有序列大，则将此数放入临时数组
			if(array[left] < array[rightStart]){
				temparray[tempIndex++] = array[left++];
			}else{
				temparray[tempIndex++] = array[rightStart++];
			}
		}
		
		//判断左序列是否结束
		while(left <= leftEnd){
			temparray[tempIndex++] = array[left++];
		}
		
		//判断右序列是否结束
		while(rightStart <= right){
			temparray[tempIndex++] = array[rightStart++];
		}
		
		//交换数组
		for(int i = 0; i < tempLength; i++){
			array[right] = temparray[right];
			right--;
		}
	}
	
	
}
