package com.yeahwell.acm.wust.aplusb1002;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a, b, n;
		n = scan.nextInt();
		while(n-- != 0){
			a = scan.nextInt();
			b = scan.nextInt();
			System.out.println(a + b);
		}
	}
}
