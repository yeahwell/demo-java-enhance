package com.yeahwell.acm.offer.count;

/**
 *
 问题描述 给定一个十进制整数N,求出从1到N的所有整数中出现”1”的个数。
  例如：N=2时 1,2出现了1个 “1” 。 
  N=12时 1,2,3,4,5,6,7,8,9,10,11,12。出现了5个“1”。
 * 
 * @author yeahwell
 *
 */
public class CountOnes {

	public long countOnes(long n) {
		long i = 0, j = 1;
		long count = 0;
		for (i = 1; i <= n; i++) {
			j = i;
			while (j != 0) {
				if (j % 10 == 1) {
					count++;
				}
				j = j / 10;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		CountOnes c = new CountOnes();
		System.out.println(c.countOnes(12));
		System.out.println(c.countOnes(120));

	}
}
