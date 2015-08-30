package com.yeahwell.dp.create.simplefactory.afteraf;

//宝马523系列  
public class FactoryBMW523 implements AbstractFactory {

	@Override
	public Engine createEngine() {
		return new EngineB();
	}

	@Override
	public Aircondition createAircondition() {
		return new AirconditionB();
	}

}