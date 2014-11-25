package com.yeahwell.dp.create.factorymothod.before;


/**
 * 货架类
 * @author yeahwell
 *
 */
public class SampleShelf {

	private String name = "普通货架";
	
	/**
	 * 存放商品
	 * @param goods
	 */
	public void put(Goods goods){
		System.out.println(this.name + "(" + this + ")" + "上摆放了" + goods.getName());
	}
}
