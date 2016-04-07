package com.yeahwell.acm.base.prime;

import java.util.BitSet;

public class Prime100 {

	public static void main(String[] args) {
		BitSet bs = new BitSet(100);
		
		//1. 第0,1位置为false，其余置为true
		for(int i = 0; i < bs.size(); i++){
			if(0 == i || 1 == i){
				bs.set(i, false);
			}else{
				bs.set(i, true);
			}
		}
		
		//2. 处理数据，找到素数
		for(int i = 0; i < bs.size(); i++){
			if(bs.get(i)){
				//内循环遍历
				for(int j = 2 * i; j < bs.size(); j += i){
					bs.set(j, false);
				}
			}
		}
		
		//3. 打印结果
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 100; i++){
			if(bs.get(i)){
				sb.append(i + ",");
				System.out.println(sb.toString());
			}
		}
		
	}
}
