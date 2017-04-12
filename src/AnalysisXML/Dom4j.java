package AnalysisXML;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4j {
	public static void main(String[] args) throws Exception {
		// ����XML�ַ���
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?");
		buffer.append("<person>");
		buffer.append("<name>TsehFischer</name>");
		buffer.append("<sex>Male</sex>");
		buffer.append("<address>Hebei</address>");
		buffer.append("</person>");
		// ͨ������XML�ַ�������Document����
		Document document = DocumentHelper.parseText(buffer.toString());
		// �õ�XML �ĸ�Ԫ��
		Element root = document.getRootElement();
		// �õ���Ԫ��person�������ӽڵ�
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		for (Element e : elementList) {
			System.out.println(e.getName() + "=>" + e.getText());
		}
		
	}
}
