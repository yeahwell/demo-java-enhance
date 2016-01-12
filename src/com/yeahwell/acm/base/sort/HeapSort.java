package com.yeahwell.acm.base.sort;

public class HeapSort {

	public void heapAdjust(int[] array, int parent, int length){
		//temp保存当前父节点
		int temp = array[parent];
		
		//得到左孩子(二叉树的定义)
		int child = 2 * parent + 1;
		
		while(child < length){
			//如果parent有右孩子，则要判断左孩子是否小于右孩子
			if(child + 1 < length && array[child] < array[child + 1]){
				child++;
			}
			 //父节点大于子节点，不用做交换
			if(temp >= array[child]){
				break;
			}
			//将较大子节点的值赋给父亲节点
			array[parent] = array[child];
			//然后将子节点做为父亲节点，已防止是否破坏根堆时重新构造
			parent = child;
			//找到该父节点左孩子节点
			child = 2 * parent + 1;
		}
		 //最后将temp值赋给较大的子节点，以形成两值交换
		array[parent] = temp;
	}
	
	public int[] heapSort(int[] array){
		int[] topNode = new int[array.length];
		//array.length / 2 - 1 表示堆中父节点的个数
		for(int i = array.length / 2 - 1; i >= 0; i--){
			heapAdjust(array, i, array.length);
		}
		
		//最后输出堆元素(求前K大)
		for(int i = array.length - 1; i > 0; i--){
			 //堆顶与当前堆的第i个元素进行值对调
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			
			//因为顺序被打乱，必须重新构造堆
			heapAdjust(array, 0, i);
		}
		
		return topNode;
	}
	
	
}
