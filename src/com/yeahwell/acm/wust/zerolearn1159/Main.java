package com.yeahwell.acm.wust.zerolearn1159;

import java.util.Scanner;

public class Main {

	public static void local1159(){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()){
			StringBuilder sb = new StringBuilder();
			sb.append(scan.nextLine());
			System.out.println(sb.reverse().toString());
		}
	}
	
	
	public static void main(String[] args) {
		local1159();
	}
}
