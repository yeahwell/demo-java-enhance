package com.yeahwell.demo.security.base64;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestBASE64 {

	/**
	 * BASE64解密
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static byte[] decryptBASE64(String key) throws IOException{
		return (new BASE64Decoder()).decodeBuffer(key);
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
	
	public static void main(String[] args) throws IOException {
		String accountName = "fuckyou";
		String afterEncrypt = encryptBASE64(accountName.getBytes());
		System.out.println(afterEncrypt);
		byte[] afterDecrypt = decryptBASE64(afterEncrypt);
		System.out.println(new String(afterDecrypt));
	}
}
