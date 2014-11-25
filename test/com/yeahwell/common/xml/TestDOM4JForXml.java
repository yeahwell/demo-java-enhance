package com.yeahwell.common.xml;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yeahwell.demo.common.xml.DOM4JForXml;

public class TestDOM4JForXml {
	
	private static DOM4JForXml dom4JForXml;
	
	@BeforeClass
	public static void beforeClass(){
		dom4JForXml = new DOM4JForXml();
	}
	
	@AfterClass
	public static void afterClass(){
		dom4JForXml = null;
	}

	@Test
	public void testParseXml(){
		dom4JForXml.parseXml("student.xml");
	}
	
	@Test
	public void testUpdateNode(){
		  Map newValue = new HashMap();
		  newValue.put("age","26");
		  dom4JForXml.updateXml("Sun", newValue, "student.xml"); 
	}
	
}
