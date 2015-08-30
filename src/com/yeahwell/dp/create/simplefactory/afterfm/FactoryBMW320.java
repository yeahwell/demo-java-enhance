package com.yeahwell.dp.create.simplefactory.afterfm;

public class FactoryBMW320 implements FactoryBMW{

	@Override
	public BMW createBMW() {
		return new BMW320();
	}

}
