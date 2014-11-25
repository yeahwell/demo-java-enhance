package com.yeahwell.common.xml;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yeahwell.demo.common.xml.SAXForXml;

public class TestSAXForXml {

	private static SAXForXml sax4Xml;

	@BeforeClass
	public static void beforeClass() {
		sax4Xml = new SAXForXml();
	}

	@AfterClass
	public static void afterClass() {
		sax4Xml = null;
	}

	@Test
	public void testSAX4Xml() throws Exception {
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		javax.xml.parsers.SAXParser sp = saxFactory.newSAXParser();
		sp.parse(new File("student.xml"), sax4Xml);
		Map contents = sax4Xml.getContents();
		Iterator keys = contents.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			System.out.println(key + ":" + contents.get(key));
		}
	}
}
