package com.yeahwell.demo.bit;

import java.util.BitSet;

public class BitSetUtil {

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
			if((bytes[i/8] & (1<<(7-i%8)%8) ) != 0)
				bitSet.set(i,true);
		}
		return bitSet;
	}
	/**
	 * 将ByteArray对象转化为BitSet
	 * 
	 * @param bytes
	 * @return
	 */
	public static BitSet byteArray2BitSet(byte[] bytes, int set , int displacement) {
		BitSet bitSet = new BitSet(bytes.length * 8 + set*displacement);
		int bytesLength = bytes.length * 8;
		for (int i = 0; i < bytesLength; i++) {
			if((bytes[i/8] & (1<<(7-i%8)%8) ) != 0)
				bitSet.set(i+set*displacement,true);
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
	
}
