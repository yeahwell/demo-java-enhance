package com.yeahwell.acm.base.search;

public class TreeSearch {

	/**
	 * 二叉排序树的插入操作
	 * @param bsTree 排序树
	 * @param key 插入数
	 * @param isExcute 是否执行了if语句
	 */
	public void insertBST(BSTree bsTree, int key, boolean isExcute){
		if(null == bsTree){
			return;
		}
		//如果父节点大于key，则遍历左子树
		if(bsTree.data > key){
			insertBST(bsTree.left, key, isExcute);
		}else{
			insertBST(bsTree.right, key, isExcute);
		}
		
		
		
	}
	
	
	
}
