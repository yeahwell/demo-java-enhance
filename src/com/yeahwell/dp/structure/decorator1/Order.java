package com.yeahwell.dp.structure.decorator1;

/**
 * 发票
 * @author yeahwell
 *
 */
public class Order implements IPrintable{

	@Override
	public void print() {
		System.out.println("发票的主体部分");
	}

}
