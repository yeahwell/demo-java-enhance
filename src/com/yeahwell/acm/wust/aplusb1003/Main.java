package com.yeahwell.acm.wust.aplusb1003;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a, b, n = 0;
		n = scan.nextInt();
		while(scan.hasNext()){
			n++;
		}
		while(n == 2){
			scan.reset();
			a = scan.nextInt();
			b = scan.nextInt();
			if(a == 0 && b == 0){
				break;
			}else{
				System.out.println(a + b);
			}
		}
	}
	
}
