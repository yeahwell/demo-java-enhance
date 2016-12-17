package com.yeahwell.demo.bytes;

public class DemoIntLong {

	public static byte[] int2Bytes(int num) {
		byte[] byteNum = new byte[4];
		for (int i = 0; i < 4; ++i) {
			int offset = 32 - (i + 1) * 8;
			byteNum[i] = (byte) ((num >> offset) & 0xff);
		}
		return byteNum;
	}

	public static int bytes2Int(byte[] byteNum) {
		int num = 0;
		for (int ix = 0; ix < 4; ++ix) {
			num <<= 8;
			num |= (byteNum[ix] & 0xff);
		}
		return num;
	}

	public static byte int2OneByte(int num) {
		return (byte) (num & 0x000000ff);
	}

	public static int oneByte2Int(byte byteNum) {
		// 针对正数的int
		return byteNum > 0 ? byteNum : (128 + (128 + byteNum));
	}

	public static byte[] long2Bytes(long num) {
		byte[] byteNum = new byte[8];
		for (int ix = 0; ix < 8; ++ix) {
			int offset = 64 - (ix + 1) * 8;
			byteNum[ix] = (byte) ((num >> offset) & 0xff);
		}
		return byteNum;
	}

	public static long bytes2Long(byte[] byteNum) {
		long num = 0;
		for (int ix = 0; ix < 8; ++ix) {
			num <<= 8;
			num |= (byteNum[ix] & 0xff);
		}
		return num;
	}
	
	/**
	 * 将byte[]转化十六进制的字符串
	 * @param bytes
	 * @return
	 */
	public static String bytes2HexString(byte[] bytes){
		String result  = "";
		for(int i = 0; i < bytes.length; i++){
			/**
			 * 将一个byte和 0xFF进行与运算，然后使用Integer.toHexString取得了十六进制字符串
			 * 为何要和 0xFF进行与运算呢?直接 Integer.toHexString(b[i]);将byte强转为int不行吗?答案是不行的
			 * 其原因在于:
				1.byte的大小为8bits而int的大小为32bits
				2.java的二进制采用的是补码形式
			 * 
			 */
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if(hex.length() == 1){
				hex = '0' + hex;
			}
			result += hex.toUpperCase();
		}
		return result;
	}

	public static void main(String[] args) {
		int num = 5;
		System.out.println("测试的int值为:" + num);

		byte[] int2bytes = int2Bytes(num);
		System.out.printf("int转成bytes: ");
		for (int i = 0; i < 4; ++i) {
			System.out.print(int2bytes[i] + "");
		}
		System.out.println();
		
		System.out.println(bytes2HexString(int2bytes));
		
		byte[] a = new byte[10];
		a[0] = -127;
		System.out.println(a[0]);
		int c = a[0] & 0xff;
		System.out.println(c);
	}

}
