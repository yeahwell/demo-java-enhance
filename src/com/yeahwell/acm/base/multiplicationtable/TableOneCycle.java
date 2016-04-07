package com.yeahwell.acm.base.multiplicationtable;

public class TableOneCycle {

	public static void main(String[] args) {
		// 一次设置两个变量
		for (int i = 1, j = 1; j <= 9; i++) {
			System.out.print(i + "*" + j + "=" + i * j + " ");
			if (i == j) {
				i = 0; // 为什么要等于0？因为执行一个循环以后，i会执行i++，会加1
				j++;
				System.out.println();
			}
		}
	}
}
