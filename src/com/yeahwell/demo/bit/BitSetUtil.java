package com.yeahwell.demo.bit;

import java.util.BitSet;

import com.yeahwell.acm.base.demobyte.ByteUtil;

public class BitSetUtil {

	public static String bytes2BinaryString(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < bytes.length; i++){
			sb.append(ByteUtil.byte2Bit(bytes[i]) + " ");
		}
		return sb.toString();
	}
	
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	public static void printBytes(byte[] bytes) {
//		System.out.println(Arrays.toString(bytes));
		System.out.println(bytes2BinaryString(bytes));
	}

	/**
	 * 将BitSet对象转化为ByteArray
	 * 
	 * @param bitSet
	 * @return
	 */
	public static byte[] bitSet2ByteArray(BitSet bitSet) {
		byte[] bytes = new byte[bitSet.size() / 8];
		for (int i = 0; i < bitSet.size(); i++) {
			int index = i / 8;
			int offset = 7 - i % 8;
			bytes[index] |= (bitSet.get(i) ? 1 : 0) << offset;
			printBytes(bytes);
		}
		return bytes;
	}

	/**
	 * 将ByteArray对象转化为BitSet
	 * 
	 * @param bytes
	 * @return
	 */
	public static BitSet byteArray2BitSet(byte[] bytes) {
		BitSet bitSet = new BitSet(bytes.length * 8);
		int bytesLength = bytes.length * 8;
		for (int i = 0; i < bytesLength; i++) {
			if ((bytes[i / 8] & (1 << (7 - i % 8) % 8)) != 0) {
				bitSet.set(i, true);
			}
			System.out.println(bitSet);
		}
		return bitSet;
	}

	/**
	 * 将ByteArray对象转化为BitSet
	 * 
	 * @param bytes
	 * @return
	 */
	public static BitSet byteArray2BitSet(byte[] bytes, int set,
			int displacement) {
		BitSet bitSet = new BitSet(bytes.length * 8 + set * displacement);
		int bytesLength = bytes.length * 8;
		for (int i = 0; i < bytesLength; i++) {
			if ((bytes[i / 8] & (1 << (7 - i % 8) % 8)) != 0) {
				bitSet.set(i + set * displacement, true);
			}
			System.out.println(bitSet);
		}
		return bitSet;
	}

	/**
	 * 将ByteArray对象转化为BitSet
	 * 
	 * @param bytes
	 * @return
	 */
	public static BitSet byteArray2BitSetMethod2(byte[] bytes) {
		BitSet bitSet = new BitSet(bytes.length * 8);
		int index = 0;
		for (int i = 0; i < bytes.length; i++) {
			for (int j = 7; j >= 0; j--) {
				bitSet.set(index++, (bytes[i] & (1 << j)) >> j == 1 ? true
						: false);
			}
		}
		return bitSet;
	}

	public static void main(String[] args) {

		BitSet a = new BitSet();
		a.set(3);
		a.set(5);
		a.set(6);
		a.set(10);

		System.out.println(a.toString());

		System.out.println("===========bitSet 2 bytes===========");
		byte[] b = BitSetUtil.bitSet2ByteArray(a);

		System.out.println("===========bytes 2 bitSet===========");
		BitSet c = BitSetUtil.byteArray2BitSet(b);
		System.out.println(c);

		System.out.println("===========bytes 2 bitSet===========");
		BitSet d = BitSetUtil.byteArray2BitSetMethod2(b);
		System.out.println(d);
	}

}
