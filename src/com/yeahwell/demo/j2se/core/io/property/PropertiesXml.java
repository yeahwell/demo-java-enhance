package com.yeahwell.demo.j2se.core.io.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertiesXml {

	public static void main(String[] args) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		
		File input = new File("testconfig.xml");
		prop.loadFromXML(new FileInputStream(input));
		
		prop.setProperty("key", "value");
		
		File output = new File("new.xml");
		prop.storeToXML(new FileOutputStream(output), "属性列表的描述");
	}
	
}
