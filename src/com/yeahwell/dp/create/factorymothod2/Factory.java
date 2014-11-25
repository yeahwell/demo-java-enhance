package com.yeahwell.dp.create.factorymothod2;

import com.yeahwell.dp.create.factorymothod1.Auto;

/**
 *工厂方法模式 
 */
public abstract class Factory {

	/**
	 * 抽象生产方法，将被子类重写
	 * @return
	 */
	abstract public Auto createAuto();
}
