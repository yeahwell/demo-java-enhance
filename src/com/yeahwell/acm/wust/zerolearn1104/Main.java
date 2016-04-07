package com.yeahwell.acm.wust.zerolearn1104;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Scanner;

public class Main {
	
	public static void local1100(){
		double a = 1 + Math.sqrt(3) / (4 - 2.1);
		Format f = new DecimalFormat("#.00");
		System.out.println(f.format(a));
	}
	
	public static void local1101(){
		Scanner scan = new Scanner(System.in);
		int a, b;
		a = scan.nextInt();
		b = scan.nextInt();
		System.out.println(a + b);
	}
	
	public static void local1102(){
		Scanner scan = new Scanner(System.in);
		int a, b;
		a = scan.nextInt();
		b = scan.nextInt();
		System.out.println(a - b);
	}
	
	public static void local1103(){
		Scanner scan = new Scanner(System.in);
		double r, h;
		double area;
		final double pi = 4.0 * Math.atan(1.0);
		Format f = new DecimalFormat("#.000");
		while(scan.hasNext()){
			r = scan.nextDouble();
			h = scan.nextDouble();
			//圆柱的表面积 = S侧+S底 = 2πrh + 2πr²
			area = 2 * pi * r * h + 2 * pi * r * r;
			System.out.println("Area=" + f.format(area));
		}
	}
	
	public static void local1104(){
		Scanner scan = new Scanner(System.in);
		Format f = new DecimalFormat("#.00");
		while(scan.hasNext()){
			String str = scan.next();
			String[] strArray = str.split(",");
			double top = Double.valueOf(strArray[0]);
			double down = Double.valueOf(strArray[1]);
			double high = Double.valueOf(strArray[2]);
			double area = (top + down) * high / 2;
			System.out.println(f.format(area));
		}
	}

	public static void main(String[] args) {
		//1094
//		System.out.println("Hello World!");
		//1095
//		System.out.println("Nice to meet you!");
		//1096
//		System.out.println(10 - 7);
		//1097
//		System.out.println( 9 * 10);
		//1098
//		System.out.println( 12 / 2);
		//1099
//		System.out.println(8.0 / 5);
		
//		local1100();
		
//		local1101();
		
//		local1102();
		
//		local1103();
		
		local1104();
		
	}
	
}
