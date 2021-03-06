package com.yeahwell.acm.offer.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TestVisitTree {

	public static class BinaryTreeNode {
		String value;

		public BinaryTreeNode(String value) {
			super();
			this.value = value;
		}

		BinaryTreeNode left;
		BinaryTreeNode right;
	}

	/**
	 * 前序遍历
	 */
	public static void visitByPreOrder(BinaryTreeNode root) {
		if (root != null) {
			System.out.print(root.value + " ");
			visitByPreOrder(root.left);
			visitByPreOrder(root.right);
		}

	}

	/**
	 * 中序遍历
	 */
	public static void visitByInOrder(BinaryTreeNode root) {
		if (root != null) {
			visitByInOrder(root.left);
			System.out.print(root.value + " ");
			visitByInOrder(root.right);
		}
	}

	/**
	 * 后序遍历
	 */
	public static void visitByPostOrder(BinaryTreeNode root) {
		if (root != null) {
			visitByPostOrder(root.left);
			visitByPostOrder(root.right);
			System.out.print(root.value + " ");
		}
	}
	
	/**
	 * 深度优先算法
	 * 对于一颗二叉树，深度优先搜索(Depth First Search)是沿着树的深度遍历树的节点，尽可能深的搜索树的分支。
	 * 利用栈，先将右子树压栈再将左子树压栈
	 * @param root
	 */
	public static void depthFirst(BinaryTreeNode root) {
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			BinaryTreeNode node = stack.peek();
			System.out.print(node.value + " ");
			stack.pop();
			if(node.right != null){
				stack.push(node.right);
			}
			if(node.left != null){
				stack.push(node.left);
			}
		}
		
	}

	/**
	 * 广度优先遍历
	 * 广度优先搜索(Breadth First Search),又叫宽度优先搜索或横向优先搜索，是从根结点开始沿着树的宽度搜索遍历
		是连通图的一种遍历策略。因为它的思想是从一个顶点V0开始，辐射状地优先遍历其周围较广的区域，故得名。
		首先是广度优先遍历，借助于一个队列来实现，首先把root放到队列当中，之后进入循环，拿出第一个元素，访问，依次把它的左右孩子放到队列当中（如果有的话），继续循环。
	 * @param root
	 */
	public static void breadthFirst(BinaryTreeNode root){
    	if(root != null){
    		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
    		BinaryTreeNode node = root;
    		queue.offer(node);
    		while(!queue.isEmpty()){
    			node = queue.poll();
    			System.out.print(node.value + " ");
    			if (node.left != null){
    				queue.offer(node.left);
    			}
    			if (node.right != null){
    				queue.offer(node.right);
    			}
    		}
    	}
    }
	
	public boolean isComplete(BinaryTreeNode root){
        if (root != null){
            Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
            BinaryTreeNode node = root;
            queue.offer(node);
            int step = 2;
            while(!queue.isEmpty()){
                node = queue.poll();
                int rightExist = node.right == null ? 0 : 1;
                int leftExist = node.left == null ? 0 : 1;
                int exist = rightExist | (leftExist << 1);

                switch(exist){
                    case 0:
                        step = 0;
                        break;
                    case 1:
                        return false;
                    case 2:
                        if (step == 2){
                            step = 1;
                            queue.offer(node.left);
                            break;
                        }else{
                            return false;
                        }
                    case 3:
                        if (step == 2){
                            queue.offer(node.left);
                            queue.offer(node.right);
                            break;
                        }else{
                            return false;
                        }
                    default:
                        break;
                }


            }
        }
        return true;
    }

	public static void main(String[] args) {

		BinaryTreeNode A = new BinaryTreeNode("A");
		BinaryTreeNode B = new BinaryTreeNode("B");
		BinaryTreeNode C = new BinaryTreeNode("C");
		BinaryTreeNode D = new BinaryTreeNode("D");
		BinaryTreeNode E = new BinaryTreeNode("E");
		BinaryTreeNode F = new BinaryTreeNode("F");
		BinaryTreeNode G = new BinaryTreeNode("G");
		BinaryTreeNode H = new BinaryTreeNode("H");
		BinaryTreeNode I = new BinaryTreeNode("I");

		A.left = B;
		A.right = C;

		B.left = D;

		C.left = E;
		C.right = F;

		E.left = G;
		E.right = H;

		F.right = I;

		TestVisitTree.visitByPreOrder(A);
		System.out.println();
		TestVisitTree.visitByInOrder(A);
		System.out.println();
		TestVisitTree.visitByPostOrder(A);
		System.out.println();
		
		TestVisitTree.depthFirst(A);
		System.out.println();
		TestVisitTree.breadthFirst(A);
		System.out.println();
		

	}

}
