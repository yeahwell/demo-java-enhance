package com.yeahwell.acm.wust.zerolearn1106;

import java.math.BigInteger;
import java.util.Scanner;


public class Main {
	
	public static String numReverse(int num){
//		String strResult = "";
//		int result = 0;
//		for(; num != 0; num /= 10){
//			if(num % 10 == 0){
//				strResult += "0";
//			}
//			result = result * 10 + num % 10;
//		}
//		return strResult + result;
		
		StringBuilder sb = new StringBuilder();
		return sb.append(num).reverse().toString();
	}

	public static void local1107(){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			int num = scan.nextInt();
			System.out.println(numReverse(num));
		}
	}
	
	public static void local1108(){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			int a = scan.nextInt();
			int b = scan.nextInt();
			System.out.println(b + " " + a);
		}
		
	}
	
	public static void local1109(){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			int n = scan.nextInt();
			int m = scan.nextInt();
			double x = (4 * n - m) / 2.0;
			double y = (m - 2 * n) / 2.0;
			if(!(x == (int)x)
					|| x < 0
					|| !(y == (int) y)
					|| y < 0
					){
				System.out.println("No");
			}else{
				System.out.println((int)x + " " + (int)y);
			}
		}
	}
	
	public static void local1120(){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			int a = scan.nextInt();
			int b = scan.nextInt();
			if(a >= b){
				System.out.println(a + " " + b);
			}else{
				System.out.println(b + " " + a);
			}
		}
	}
	
	public static void local1121(){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			BigInteger a = scan.nextBigInteger();
			BigInteger b = scan.nextBigInteger();
			BigInteger c = scan.nextBigInteger();
			BigInteger[] array = {a, b, c};
			BigInteger[] result = bubbleSort(array);
			for(int i = 0; i < result.length; i++){
				System.out.print(result[i] + " ");
			}
			System.out.println();
		}
	}
	
	public static BigInteger[] bubbleSort(BigInteger[] array){
		BigInteger temp ; 
		for(int i = 0; i < array.length; i++){
			for(int j = array.length - 1; j > i; j--){
				if(array[j - 1].compareTo(array[j]) < 0){
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	
	public static void main(String[] args) {
//		local1107();
//		local1108();
//		local1109();
//		local1120();
		
		local1121();
	}
	
}
