package com.yeahwell.acm.base.demobyte;

/**
网络上一个字节标识的是-128 到 127 之间的数字信息，而Java中Read到的是0 到 255 之间的无符号数字！ 
如果需要解析含义，则需要进行符号转换！
 * @author yeahwell
 *
 */
public class UnsignedByte {

	public static void main(String[] args) {
		// 有符号变无符号
		int b = -1;
		int i = b >= 0 ? b : 256 + b;
		System.out.println(i);

		// 无符号变有符号
		int x = 255;
		int y = 127 - x;
		System.out.println(y);
	}
	
	
}
