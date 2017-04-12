package AnalysisXML;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4j {
	public static void main(String[] args) throws Exception {
		// 构造XML字符串
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?");
		buffer.append("<person>");
		buffer.append("<name>TsehFischer</name>");
		buffer.append("<sex>Male</sex>");
		buffer.append("<address>Hebei</address>");
		buffer.append("</person>");
		// 通过解析XML字符串创建Document对象
		Document document = DocumentHelper.parseText(buffer.toString());
		// 得到XML 的根元素
		Element root = document.getRootElement();
		// 得到根元素person的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		for (Element e : elementList) {
			System.out.println(e.getName() + "=>" + e.getText());
		}
		
	}
}
