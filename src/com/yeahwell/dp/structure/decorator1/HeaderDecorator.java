package com.yeahwell.dp.structure.decorator1;

public class HeaderDecorator extends OrderDecorator{

	/**
	 * 构造方法
	 * @param printable  装饰对象
	 */
	public HeaderDecorator(IPrintable printable) {
		super(printable);
	}
	
	@Override
	public void print(){
		/**
		 * 在打印方法之前打印发票头部
		 */
		System.out.println("发票头部");
		
		/**
		 * 打印装饰对象的方法
		 */
		super.print();
	}
}
