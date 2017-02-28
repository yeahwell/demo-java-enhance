package com.yeahwell.demo.j2se.core.collect.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * ArrayList的随机访问效率比LinkedList高出好几个数量级。
 * @author yeahwell
 *
 */
public class ListTest2 {

	public static void main(String[] args) {
		
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {

		}
		
		
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		for (int i = 0; i < 100000; i++) {
			linkedList.add(i);
		}

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 0; i < 100000; i++) {
			arrayList.add(i);
		}

		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			int j = random.nextInt(i + 1);
			int k = linkedList.get(j);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		for (int i = 0; i < 100000; i++) {
			int j = random.nextInt(i + 1);
			int k = arrayList.get(j);
		}
		System.out.println(System.currentTimeMillis() - end);
	}
}