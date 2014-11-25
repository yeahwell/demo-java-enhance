package com.yeahwell.demo.common.xml;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXForXml extends DefaultHandler {

	private Stack tags = new Stack();
	private Map contents = new LinkedHashMap();
	public int count = 0;

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String tag = (String) tags.peek();
		if ("name".equals(tag)) {
			String name = new String(ch, start, length);
			contents.put("name" + count, name);
		}
		if ("age".equals(tag)) {
			contents.put("age" + count, new String(ch, start, length));
		}
		if ("sex".equals(tag)) {
			contents.put("sex" + count, new String(ch, start, length));
		}
		if ("address".equals(tag)) {
			contents.put("address" + count, new String(ch, start, length));
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("student".equals(qName)) {
			count++;
		}
		tags.push(qName);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		tags.pop();
	}

	public Map getContents() {
		return contents;
	}
}
