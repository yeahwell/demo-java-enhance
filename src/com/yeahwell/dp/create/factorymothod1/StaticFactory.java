package com.yeahwell.dp.create.factorymothod1;

/**
 * 静态工厂
 * 优点：
 * 1. 用代码和具体使用类的耦合度降低
 * 2. 令创建与使用的代码相分离，可以独立地变化，易于维护和拓展
 * 3. 可以通过外部配置的方法将耦合度进一步降低
 * 缺点：
 * 1. 产品类有复杂多层次等级结构时，工厂类只有一个
 * 2. 这个工厂类集中了所有的产品创建逻辑，God Class，若这个类不能正常工作，整个程序都会受到影响
 * 3. 静态工厂模式使用静态方法作为工厂方法，而静态方法无法由子类继承，了因此工厂的可拓展性受到限制
 * 
 */
public class StaticFactory {

	public static Auto createAuto(int autoId){
		switch(autoId){
		case 1:
			return new Car();
		case 2:
			return new Bus();
		case 3:
			return new Truck();
		default:
			/**
			 * 如果没有此种车型,抛出运行时异常
			 */
			throw new RuntimeException("没有这种车型");
		}
	}
}
