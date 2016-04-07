package com.yeahwell.dp.behavior.strategy10;

/**
 * 辅助类
 * @author yeahwell
 *
 */
public abstract class AbstractCalculator {

	public int[] split(String exp, String opt){
		String array[] = exp.split(opt);
		int[] arrayInt = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}
}
