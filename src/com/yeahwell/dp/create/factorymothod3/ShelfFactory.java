package com.yeahwell.dp.create.factorymothod3;

public abstract class ShelfFactory {

	/**
	 * 所产生的货架容纳货物的最大宽度
	 */
	private int maxWidth;

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}
	
	abstract public Shelf createShelf();
	
}
