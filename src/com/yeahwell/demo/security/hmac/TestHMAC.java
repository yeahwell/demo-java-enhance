package com.yeahwell.demo.security.hmac;

import java.io.IOException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import static org.junit.Assert.*;  
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestHMAC {

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256  
	 * HmacSHA384  
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static byte[] decryptBASE64(String key) throws IOException {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密 BASE加密后产生的字节位数是8的倍数，如果不够位数以=符号填充
	 * 
	 * @param key
	 * @return
	 */
	public static String encryptBASE64(byte[] key) {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}

	@Test
	public void test() throws Exception {
		String inputStr = "ilovechina";
		byte[] inputData = inputStr.getBytes();
		String key = initMacKey();
		System.err.println("Mac密钥:\n" + key);
		byte[] afterHMAC1 = encryptHMAC(inputData, key);
		byte[] afterHMAC2 = encryptHMAC(inputData, key);
		assertArrayEquals(encryptHMAC(inputData, key), encryptHMAC(inputData, key));  
		System.out.println(new String(afterHMAC1));
	}

}
