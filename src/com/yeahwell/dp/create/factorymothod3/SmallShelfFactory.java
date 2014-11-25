package com.yeahwell.dp.create.factorymothod3;

public class SmallShelfFactory extends ShelfFactory{

	public SmallShelfFactory(){
		super();
		//设置最大宽度
		this.setMaxWidth(new SmallShelf().getMaxWidth());
	}
	
	@Override
	public Shelf createShelf() {
		Shelf s = new SmallShelf();
		return s;
	}

	
}
