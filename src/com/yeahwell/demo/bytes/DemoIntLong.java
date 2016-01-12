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

	public static void main(String[] args) {
		int num = 5;
		System.out.println("测试的int值为:" + num);

		byte[] int2bytes = int2Bytes(num);
		System.out.printf("int转成bytes: ");
		for (int i = 0; i < 4; ++i) {
			System.out.print(int2bytes[i] + " ");
		}
		System.out.println();
	}

}
