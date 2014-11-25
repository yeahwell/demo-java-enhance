package com.yeahwell.dp.create.factorymothod3;


/**
 * 货架抽象类
 */
public abstract class Shelf {

	/**
	 * 货架容纳货物的最大宽度
	 * 以cm做单位
	 */
	private int maxWidth;
	
	private String shelfName;

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	
	public void put(Goods goods){
		System.out.println("将" + goods.getName() + "放入" + this.getShelfName());
	}
}
