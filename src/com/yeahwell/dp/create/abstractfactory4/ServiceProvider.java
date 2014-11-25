package com.yeahwell.dp.create.abstractfactory4;

import javax.xml.parsers.DocumentBuilderFactory;

public class ServiceProvider {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		System.out.println(factory.getClass());
	}
}
