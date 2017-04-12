package AnalysisXML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class XStreamXML {
	/*
	 * JAVA ����ת�� XML
	 */
	public static String javaObject2Xml(Person person) {
		XStream xs = new XStream(new DomDriver());
		// ��Person�ඨ�����
		xs.alias("Person", person.getClass());
		return xs.toXML(person);
	}

	/*
	 * XML����ת��JAVA����
	 */
	public static Object xml2JavaObject(String xml) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("Person", Person.class);
		Person person = (Person) xs.fromXML(xml);
		return person;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����Person����
		Person p1 = new Person();
		p1.setName("������");
		p1.setSex("��");
		p1.setAddress("�ӱ�ʡ������������");
		// ��P1ת��ΪXML����
		System.out.println(javaObject2Xml(p1));
		
		String xml = "<person><name>������</name><sex>��</sex><address>�ӱ�ʡ������</address></person>";
		
		Person p2 = (Person)xml2JavaObject(xml);
		
		System.out.println(p2.getName() + "" + p2.getSex() + "" + p2.getAddress());
		
	}
}

class Person {
	private String name;
	private String sex;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
}
