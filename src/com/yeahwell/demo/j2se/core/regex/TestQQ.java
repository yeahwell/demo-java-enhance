package com.yeahwell.demo.j2se.core.regex;

import org.junit.Test;

public class TestQQ {

	/**
	 * QQ号5 ~ 15位，0不能开头，只能是数字
	 * 
	 */
	@Test
	public void checkQQ(){
		String qq = "123454fdsafdsaf6";
		int len = qq.length();
		if(len >= 5 && len <= 15){
			if(!qq.startsWith("0")){
				
/*              //方法1:
  				char[] chs = qq.toCharArray();
				boolean flag = true;
				for(int i = 0; i < len; i++){
					if(!(chs[i] >= '0' && chs[i] <= '9')){
						flag = false;
						break;
					}
				}
				
				if(flag){
					System.out.println("QQ号符合标准");
				}else{
					System.out.println("QQ号中有非法字符");
				}*/
				
				try{
					Long l = Long.parseLong(qq);
					System.out.println("QQ号" + l + "符合规范");
				}
				catch(NumberFormatException nfe){
					System.out.println("出现非法字符");
				}
			}else{
				System.out.println("不能以0开头");
			}
		}else{
			System.out.println("长度错误");
		}
	}
}
