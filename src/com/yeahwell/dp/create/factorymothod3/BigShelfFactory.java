package com.yeahwell.dp.create.factorymothod3;

public class BigShelfFactory extends ShelfFactory{

	public BigShelfFactory(){
		super();
		//设置最大宽度
		this.setMaxWidth(new BigShelf().getMaxWidth());
	}
	
	@Override
	public Shelf createShelf() {
		Shelf s = new BigShelf();
		return s;
	}

	
}
