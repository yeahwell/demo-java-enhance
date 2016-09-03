package com.yeahwell.demo.j2se.core.io.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		File input = new File("testconfig.properties");
		prop.load(new FileInputStream(input));
		//prop.load(new FileReader(input));
		
		System.out.println(prop.getProperty("admin_username"));
		prop.setProperty("key", "value");
		
		File output = new File("new.properties");
		prop.store(new FileOutputStream(output), "属性列表的描述");
//		prop.store(new FileWriter(output), "属性列表的描述");
	}
	
	
}
