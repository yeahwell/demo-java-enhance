package com.yeahwell.demo.bit;

import java.util.BitSet;

public class TestBitSetAcct {
	
	public static final String NUMBER_ZERO = "0";
	public static final String NUMBER_ONE = "1";
	
	
	public static int acct2int(String acctAsset){
		if(null != acctAsset && acctAsset.length() >= 10){
			String temp = acctAsset.substring(acctAsset.length() - 10, acctAsset.length());
			System.out.println(temp);
			return Integer.parseInt(temp);
		}
		return -1;
	}

	public static String bitSet2String(BitSet southFundAcctSet){
		StringBuilder sb = new StringBuilder();
		int setLength = southFundAcctSet.length();
		for(int i = setLength -1; i >= 0; i--){
			if(southFundAcctSet.get(i)){
				sb.append(NUMBER_ONE);
			}else{
				sb.append(NUMBER_ZERO);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		Integer acctAsset = acct2int("291000001000010000154158");
		System.out.println(acctAsset);
		
		BitSet southFundAcctSet = new BitSet(20000000);
		southFundAcctSet.set(3, true);
		southFundAcctSet.set(5, true);
		southFundAcctSet.set(6, true);
		southFundAcctSet.set(acctAsset -1, true);
		System.out.println("运算前: 南方 = " + southFundAcctSet);
		
		BitSet pafAcctSet = new BitSet(2000000);
		pafAcctSet.set(2, true);
		pafAcctSet.set(3, true);
		pafAcctSet.set(4, true);
		pafAcctSet.set(5, true);
		pafAcctSet.set(7, true);
		pafAcctSet.set(9, true);
		System.out.println("运算前: 平安付 = " + pafAcctSet);
		
		long startTime = System.currentTimeMillis();
		BitSet xorResult = (BitSet) southFundAcctSet.clone();
		xorResult.xor(pafAcctSet);
		System.out.println("运算结果: 耗时=" + (System.currentTimeMillis() - startTime) + " 异或结果\t" + xorResult);
		
		startTime = System.currentTimeMillis();
		BitSet southFundHasPafNo = (BitSet) southFundAcctSet.clone();
		southFundHasPafNo.and(xorResult);
		System.out.println("运算结果: 耗时=" + (System.currentTimeMillis() - startTime) + " 南方有，平安付无\t" + southFundHasPafNo);
		
		startTime = System.currentTimeMillis();
		BitSet pafHasSouthFundNo = (BitSet) pafAcctSet.clone();
		pafHasSouthFundNo.and(xorResult);
		System.out.println("运算结果: 耗时=" + (System.currentTimeMillis() - startTime) + " 平安付有，南方无\t" + pafHasSouthFundNo);
		
		System.out.println(southFundAcctSet + "\t" + southFundAcctSet.size());
//		int card = southFundAcctSet.cardinality();
		 for (int i = southFundAcctSet.nextSetBit(0); i >= 0; i = southFundAcctSet.nextSetBit(i+1)) {
		     // operate on index i here
			 System.out.println(i);
		     if (i == Integer.MAX_VALUE) {
		         break; // or (i+1) would overflow
		     }
		 }
		System.out.println(pafAcctSet + "\t" + pafAcctSet.size());
	}
	
	
}
