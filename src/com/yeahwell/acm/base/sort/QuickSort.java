package com.yeahwell.acm.base.sort;

public class QuickSort {
	
	public int division(int[] array, int left, int right){
		int baseNum = array[left];
		while(left < right){
			
			//从数组的右端开始向前找，一直找到比base小的数字为止(包括base同等数)
			while(left < right && array[right] >= baseNum){
				right = right - 1;
			}
			
			//最终找到了比baseNum小的元素，要做的事情就是此元素放到base的位置
			array[left] = array[right];
			
			//从数组的左端开始向后找，一直找到比base大的数字为止（包括base同等数）
			while(left < right && array[left] <= baseNum){
				left = left + 1;
			}
			 //最终找到了比baseNum大的元素，要做的事情就是将此元素放到最后的位置
			array[right] = array[left];
		}
		
		//最后就是把baseNum放到该left的位置
		 array[left] = baseNum;
		//最终，我们发现left位置的左侧数值部分比left小，left位置右侧数值比left大
		return left;
	}

	public void quickSort(int[] array, int left, int right){
		//左下标一定小于右下标，否则就超越了
		if(left < right){
			
			//对数组进行分割，去除下次分割的基准标号
			int i = division(array, left, right);
			
			//对"基准标号"左侧的一组数值进行递归的切割，将这些数值完整的排序
			quickSort(array, left, i - 1);
			
			//对"基准标号"右侧的一组数值进行递归的切割，将这些数值完整的排序
			quickSort(array, i + 1, right);
		}
	}
	
	
}
