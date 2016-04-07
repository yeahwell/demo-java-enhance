package com.yeahwell.acm.base.bit;

public class DemoBit {

	public void int2byte() {
		// 1. int转byte
		int a = 21;
		byte b = (byte) a;
		System.out.println(b);
	}

	public void byte2int() {
		// 2. byte转int
		byte c = 21;
		int d = c;
		System.out.println(d);
	}

	public void bitMove() {
		System.out.println(11 << 2);
		System.out.println(11 << 34);
		System.out.println(1111111111111111111L >>> 2);
		System.out.println(1111111111111111111L >>> 66);

	}

	public void bitMove2() {
		// 左移
		int i = 12; // 二进制为:0000000000000000000000000001100
		i <<= 2; // i左移2位，把高位的两位数字(左侧开始)抛弃,低位的空位补0,二进制码就为0000000000000000000000000110000
		System.out.println(i); // 二进制110000值为48；
		// 右移
		i >>= 2; // i右移2为，把低位的两个数字(右侧开始)抛弃,高位整数补0，负数补1，二进制码就为0000000000000000000000000001100
		System.out.println(i); // 二进制码为1100值为12
		// 右移example
		int j = 11;// 二进制码为00000000000000000000000000001011
		j >>= 2; // 右移两位，抛弃最后两位,整数补0,二进制码为：00000000000000000000000000000010
		System.out.println(j); // 二进制码为10值为2
		byte k = -2; // 转为int,二进制码为：1000000000000000000000000000010
		k >>= 2; // 右移2位，抛弃最后2位，负数补1,二进制码为：11000000000000000000000000000
		System.out.println(k); // 二进制码为11值为2

	}

	public void bitLogic() {
		String binary[] = { "0000", "0001", "0010", "0011", "0100", "0101",
				"0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101",
				"1110", "1111" };
		int a = 3; // 0 + 2 + 1 or 0011 in binary
		int b = 6; // 4 + 2 + 0 or 0110 in binary
		int c = a | b;
		int d = a & b;
		int e = a ^ b;
		int f = (~a & b) | (a & ~b);
		int g = ~a & 0x0f;
		System.out.println(" a = " + binary[a]);
		System.out.println(" b = " + binary[b]);
		System.out.println(" a|b = " + binary[c]);
		System.out.println(" a&b = " + binary[d]);
		System.out.println(" a^b = " + binary[e]);
		System.out.println("~a&b|a&~b = " + binary[f]);
		System.out.println(" ~a = " + binary[g]);
	}

	public void byteShift() {
		byte a = 64, b;
		int i;
		i = a << 2;
		b = (byte) (a << 2);
		System.out.println("Original value of a: " + a);
		System.out.println("i and b: " + i + " " + b);
	}

	public void multBy2() {
		int i;
		int num = 0xFFFFFFE;
		for (i = 0; i < 4; i++) {
			num = num << 1;
			System.out.println(num);
		}
	}

	public void hexByte() {
		//下面的例子将一个byte 型的值转换为用十六进制表示。
		//注意右移后的值与0x0f进行按位与运算，这样可以舍弃任何的符号位扩展，以便得到的值可以作为定义数组的下标，从而得到对应数组元素代表的十六进制字符。 
		char hex[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };
		byte b = (byte) 0xf1;
		System.out.println("b = 0x" + hex[(b >> 4) & 0x0f] + hex[b & 0x0f]);

	}

}
