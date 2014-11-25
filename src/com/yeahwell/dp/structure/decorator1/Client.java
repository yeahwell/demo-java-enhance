package com.yeahwell.dp.structure.decorator1;

public class Client {

	public static void main(String[] args) {
		
		IPrintable order = new Order();
		order.print();
		System.out.println("-------打印完毕------");
		System.out.println();
		
		IPrintable headerOrder = new HeaderDecorator(new Order());
		headerOrder.print();
		System.out.println("-------打印完毕------");
		System.out.println();
		
		IPrintable footerOrder = new FooterDecorator(new Order());
		footerOrder.print();
		System.out.println("-------打印完毕------");
		System.out.println();
		
		IPrintable headerAndFooterOrder = new HeaderDecorator(new FooterDecorator(new Order()));
		headerAndFooterOrder.print();
		System.out.println("-------打印完毕------");
		System.out.println();
		
	}
}
