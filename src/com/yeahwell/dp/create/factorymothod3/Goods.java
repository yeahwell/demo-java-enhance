package com.yeahwell.dp.create.factorymothod3;

/**
 * 
 * @author yeahwell
 * 
 */
public abstract class Goods {

	/**
	 * 货品名称 
	 * 以厘米作单位
	 */
	private String name;

	/**
	 * 货物宽度
	 */
	private int width;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
}
