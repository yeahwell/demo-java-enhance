package com.yeahwell.dp.create.factorymothod4;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * JDK中工厂方法的使用
 * @author yeahwell
 *
 */
public class FactoryDemo {

	public static void main(String[] args) throws ParserConfigurationException {
		/**
		 * 工厂方法创建DocumentBuilder
		 * 
		 */
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
	
		/**
		 * 工厂方法模式创建布尔类型的实例
		 */
		Boolean v = Boolean.valueOf(true);
		
	}
	

}
