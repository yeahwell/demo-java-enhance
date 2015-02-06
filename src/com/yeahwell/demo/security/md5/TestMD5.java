package com.yeahwell.demo.security.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class TestMD5 {
	
	private final static String KEY_MD5 = "MD5";
	
	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();

	}

	/**
	 * BASE64加密
	 * BASE加密后产生的字节位数是8的倍数，如果不够位数以=符号填充
	 * @param key
	 * @return
	 */
	public static String encryptBASE64(byte[] key){
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String file = "ilovechina";
		byte[] afterMD5 = encryptMD5(file.getBytes());
		System.out.println(new String(afterMD5));
		String afterBASE64 = encryptBASE64(afterMD5);
		System.out.println(afterBASE64);
	}
}
