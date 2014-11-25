package com.yeahwell.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 正则表达式： 符合一定规则的表达式
 * 作用： 用于专门操作字符串
 * @author admin
 *
 */
public class TestRegex1 {

	@Test
	public void test1() {
		
		String target = "catabdcatdetfcat";
		String regex = "";
		regex = "^cat";
		regex = "cat$";
		//regex = "cat*";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(target);
		boolean isMatches = matcher.matches();
		
		System.out.println("是否符合正则表达式" + isMatches);
		
	}
	
	@Test
	public void test2() {
		
		String target = "aaaaab";
		String regex = "";
		regex = "a*b";
		regex = "b$";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(target);
		boolean isMatches = matcher.matches();
		
		System.out.println("是否符合正则表达式" + isMatches);
		
	}

}
