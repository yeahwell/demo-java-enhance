package com.yeahwell.demo.security.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class TestSHA {
	
	private static final String KEY_SHA = "SHA";

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws NoSuchAlgorithmException{
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
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
		byte[] afterSHA = encryptSHA(file.getBytes());
		System.out.println(new String(afterSHA));
		String afterBASE64 = encryptBASE64(afterSHA);
		System.out.println(afterBASE64);
	}
	
}
