package com.yeahwell.demo.common.xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class JDOMForXml {

	/**
	 * 根据文件得到Document对象
	 * 
	 * @param fileName
	 *            将要被解析的XML文件
	 * @return Document对象
	 */
	public Document getDocument(String fileName) {
		// 构造
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			// 读取文档
			document = builder.build(new File(fileName));
			System.out.println("文档已钝化");
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	public void parseXml(String fileName) {
		Document document = getDocument(fileName);
		// 得到根
		Element element = document.getRootElement();
		// 得到下级节点
		List allChildren = element.getChildren();
		
		for (int i = 0; i < allChildren.size(); i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("姓名：" + ((Element)allChildren.get(i)).getChild("name").getText());
			sb.append(" , ");
			sb.append("年龄：" + ((Element)allChildren.get(i)).getChild("age").getText());
			sb.append(" , ");
			sb.append("性别：" + ((Element)allChildren.get(i)).getChild("sex").getText());
			sb.append(" , ");
			sb.append("地址：" + ((Element)allChildren.get(i)).getChild("address").getText());
			sb.append(" , ");
			System.out.println(sb.toString());
		}
	}

}
