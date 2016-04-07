package com.yeahwell.dp.behavior.strategy10;

/**
 * 策略模式的决定权在用户，系统本身提供不同算法的实现，新增或者删除算法，对各种算法做封装。
 * 因此，策略模式多用在算法决策系统中，外部用户只需要决定用哪个算法即可。
 * @author yanjiawei
 *
 */
public class StrategyTest {

	public static void main(String[] args) {
		String exp = "8-2";
		ICalculator cal = new Minus();
		int result = cal.calculate(exp);
		System.out.println(exp + "=" + result);
	}
	
	
}
