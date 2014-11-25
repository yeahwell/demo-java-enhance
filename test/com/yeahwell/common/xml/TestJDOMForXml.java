package com.yeahwell.common.xml;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yeahwell.demo.common.xml.JDOMForXml;

public class TestJDOMForXml {
	
	private static JDOMForXml jdomForXml;
	
	@BeforeClass
	public static void beforeClass(){
		jdomForXml = new JDOMForXml();
	}
	
	@AfterClass
	public static void afterClass(){
		jdomForXml = null;
	}

	@Test
	public void testParseXml(){
		jdomForXml.parseXml("student.xml");
	}
	
}
