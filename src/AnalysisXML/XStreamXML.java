package AnalysisXML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class XStreamXML {
	/*
	 * JAVA 对象转化 XML
	 */
	public static String javaObject2Xml(Person person) {
		XStream xs = new XStream(new DomDriver());
		// 给Person类定义别名
		xs.alias("Person", person.getClass());
		return xs.toXML(person);
	}

	/*
	 * XML对象转化JAVA对象
	 */
	public static Object xml2JavaObject(String xml) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("Person", Person.class);
		Person person = (Person) xs.fromXML(xml);
		return person;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//构建Person对象
		Person p1 = new Person();
		p1.setName("张文哲");
		p1.setSex("男");
		p1.setAddress("河北省邯郸市永年县");
		// 将P1转换为XML对象
		System.out.println(javaObject2Xml(p1));
		
		String xml = "<person><name>张文哲</name><sex>男</sex><address>河北省邯郸市</address></person>";
		
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
