package com.yeahwell.common.xml;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yeahwell.demo.common.xml.DOMForXml;

public class TestDOMForXml {
	
	private static DOMForXml domForXml;
	
	@BeforeClass
	public static void beforeClass(){
		domForXml = new DOMForXml();
	}
	
	@AfterClass
	public static void afterClass(){
		domForXml = null;
	}

	@Test
	public void testParseXml(){
		domForXml.parseXml("student.xml");
	}

	@Test
	public void testAddNode(){
		domForXml.addNode("Sun", "20", "Man", "USA");
	}
	
	@Test
	public void testDeleteNode(){
		domForXml.deleteNode("sun");
	}
	
	@Test
	public void testUpdateNode(){
		domForXml.updateNode("sun");
	}
	
}
