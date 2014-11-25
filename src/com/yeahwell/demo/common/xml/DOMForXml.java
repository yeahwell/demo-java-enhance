package com.yeahwell.demo.common.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMForXml {

	/**
	 * 根据文件得到Document对象
	 * 
	 * @param fileName
	 *            将要被解析的XML文件
	 * @return Document对象
	 */
	public Document getDocument(String fileName) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * 将改动持久到文件
	 * 
	 * @param doc
	 * @param distFileName
	 */
	public void modifyFile(Document doc, String distFileName) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer tfer = tf.newTransformer();
			DOMSource dsource = new DOMSource(doc);
			StreamResult sr = new StreamResult(new File("student.xml"));
			tfer.transform(dsource, sr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析
	 * 
	 * @param fileName
	 */
	public void parseXml(String fileName) {
		Document document = getDocument(fileName);
		NodeList nodeList = document.getElementsByTagName("student");
		for (int i = 0; i < nodeList.getLength(); i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("姓名："
					+ document.getElementsByTagName("name").item(i)
							.getFirstChild().getNodeValue());
			sb.append(" , ");
			sb.append("年龄："
					+ document.getElementsByTagName("age").item(i)
							.getFirstChild().getNodeValue());
			sb.append(" , ");
			sb.append("性别："
					+ document.getElementsByTagName("sex").item(i)
							.getFirstChild().getNodeValue());
			sb.append(" , ");
			sb.append("地址："
					+ document.getElementsByTagName("address").item(i)
							.getFirstChild().getNodeValue());
			System.out.println(sb.toString());
		}
	}

	/**
	 * 创建一个新的学生
	 * 
	 * @param name
	 * @param age
	 * @param sex
	 * @param address
	 */
	public void addNode(String name, String age, String sex, String address) {
		try {
			Document document = getDocument("student.xml");
			NodeList nodeList = document.getElementsByTagName("students");
			// 创建新的节点
			Node studentNode = document.createElement("student");
			Node nameNode = document.createElement("name");
			nameNode.appendChild(document.createTextNode(name));
			Node ageNode = document.createElement("age");
			ageNode.appendChild(document.createTextNode(age));
			Node sexNode = document.createElement("sex");
			sexNode.appendChild(document.createTextNode(sex));
			Node addressNode = document.createElement("address");
			addressNode.appendChild(document.createTextNode(address));
			// 添加节点
			studentNode.appendChild(nameNode);
			studentNode.appendChild(ageNode);
			studentNode.appendChild(sexNode);
			studentNode.appendChild(addressNode);
			nodeList.item(0).appendChild(studentNode);
			// 此时真正的处理将新数据添加到文件中（磁盘）
			modifyFile(document, "student.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		parseXml("student.xml");
	}

	/**
	 * 删除一个节点
	 * 
	 * @param name
	 *            被删除的节点的学生姓名
	 */
	public void deleteNode(String name) {
		Document document = getDocument("student.xml");
		NodeList nodeList = document.getElementsByTagName("name");
		for (int i = 0; i < nodeList.getLength(); i++) {
			String value = nodeList.item(i).getFirstChild().getTextContent();
			if (name != null && name.equalsIgnoreCase(value)) { // 忽略大小写
				Node parentNode = nodeList.item(i).getParentNode();
				document.getFirstChild().removeChild(parentNode);
			}
		}
		modifyFile(document, "student.xml");
		parseXml("student.xml");  //输出
	}

	/**
	 * 根据name修改某个节点的内容
	 * 
	 * @param name
	 */
	public void updateNode(String name) {
		Document document = getDocument("student.xml");
		NodeList nodeList = document.getElementsByTagName("name");
		for (int i = 0; i < nodeList.getLength(); i++) {
			String value = nodeList.item(i).getFirstChild().getTextContent();
			if (name != null && name.equalsIgnoreCase(value)) {
				Node parentNode = nodeList.item(i).getParentNode();
				NodeList nl = parentNode.getChildNodes();
				for (int j = 0; j < nl.getLength(); j++) {
					String modifyNode = nl.item(j).getNodeName();
					if (modifyNode.equalsIgnoreCase("age")) {
						nl.item(j).getFirstChild().setTextContent("25");
					}
				}
			}
		}
		modifyFile(document, "student.xml");
		parseXml("student.xml");  //输出
	}
}
