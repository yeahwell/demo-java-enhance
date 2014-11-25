package com.yeahwell.dp.create.factorymothod1;

/**
 * 抽象汽车类
 * @author yeahwell
 *
 */
public abstract class Auto {
   
	/**
	 * 车型名称
	 */
	private String name;
	
	/**
	 * 抽象的工作方式
	 */
	abstract public void run();

	/**
	 * 获得车型名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置车型名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
