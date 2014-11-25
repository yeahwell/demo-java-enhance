package com.yeahwell.dp.structure.decorator1;

public abstract class OrderDecorator implements IPrintable{

	/**
	 * 用于装饰的私有对象
	 */
	private IPrintable printable;
	
	/**
	 * 构造方法
	 * @param printable  装饰对象
	 */
	public OrderDecorator(IPrintable printable) {
		super();
		this.printable = printable;
	}
	
	/**
	 * 实现接口方法，注意此处调用的装饰对象的对应方法
	 */
	public void print(){
		printable.print();
	}
}
