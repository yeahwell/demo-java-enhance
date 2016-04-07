package com.yeahwell.acm.base.bitmap;


public class Bitmap {

	private byte[] bitMap = null;

	public Bitmap(int size) {
		if (size % 8 == 0) {
			bitMap = new byte[size / 8];
		} else {
			bitMap = new byte[size / 8 + 1];
		}
	}

	public void setTag(int number) {
		int index = 0;
		int bitIndex = 0;
		if (number % 8 == 0) {
			index = number / 8 - 1;
			bitIndex = 8;
		} else {
			index = number / 8;
			bitIndex = number % 8;
		}
		switch (bitIndex) {
		case 1:
			bitMap[index] = (byte) (bitMap[index] | 0x01);
			break;
		case 2:
			bitMap[index] = (byte) (bitMap[index] | 0x02);
			break;
		case 3:
			bitMap[index] = (byte) (bitMap[index] | 0x04);
			break;
		case 4:
			bitMap[index] = (byte) (bitMap[index] | 0x08);
			break;
		case 5:
			bitMap[index] = (byte) (bitMap[index] | 0x10);
			break;
		case 6:
			bitMap[index] = (byte) (bitMap[index] | 0x20);
			break;
		case 7:
			bitMap[index] = (byte) (bitMap[index] | 0x40);
			break;
		case 8:
			bitMap[index] = (byte) (bitMap[index] | 0x8);
			break;
		}
	}

	// 打印bit为1 的数值
	public void printBitMap() {
		int size = bitMap.length;
		for (int i = 0; i < size; i++) {
			byte temp = bitMap[i];

			if ((bitMap[i] & 0x01) == 1) {
				System.out.print(i * 8 + 1 + " ");
			}
			if ((bitMap[i] >> 1 & 0x01) == 1) {
				System.out.print(i * 8 + 2 + " ");
			}
			if ((bitMap[i] >> 2 & 0x01) == 1) {
				System.out.print(i * 8 + 3 + " ");
			}
			if ((bitMap[i] >> 3 & 0x01) == 1) {
				System.out.print(i * 8 + 4 + " ");
			}

			if ((bitMap[i] >> 4 & 0x01) == 1) {
				System.out.print(i * 8 + 5 + " ");
			}
			if ((bitMap[i] >> 5 & 0x01) == 1) {
				System.out.print(i * 8 + 6 + " ");
			}
			if ((bitMap[i] >> 6 & 0x01) == 1) {
				System.out.print(i * 8 + 7 + " ");
			}
			if ((bitMap[i] >> 7 & 0x01) == 1) {
				System.out.print(i * 8 + 8 + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Bitmap map = new Bitmap(10);
		map.setTag(1);
		map.setTag(5);
		map.setTag(4);
		map.setTag(2);
		map.setTag(11);
		map.printBitMap();
	}

}
