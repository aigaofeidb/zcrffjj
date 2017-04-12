package MenuBtn;

import net.sf.json.JSONObject;

//import com.baidu.bdt.java.util.json.JSONObject;

public class CreateMenu {
	public static void main(String[] args) {
		ClickButton btn1 = new ClickButton();
		btn1.setName("���ո���");
		btn1.setType("click");
		btn1.setKey("V1001_TODAY_MUSIC");

		ViewButton btn2 = new ViewButton();
		btn2.setName("���ּ��");
		btn2.setType("view");
		btn2.setUrl("http://www.qq.com");

		ClickButton btn31 = new ClickButton();
		btn31.setName("hello world");
		btn31.setType("click");
		btn31.setKey("V1001_HELLO_WORLD");

		ClickButton btn32 = new ClickButton();
		btn32.setName("������һ��");
		btn32.setType("click");
		btn32.setKey("V1001_GOOD");

		// ���ϰ�ť����2��click���͵İ�ť
		Complexbutton btn3 = new Complexbutton();
		btn3.setName("�˵�");
		btn3.setSub_button(new Button[] { btn31, btn32 });

		// �����˵�����
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn1, btn2, btn3 });

		// ���˵�����ת��ΪJSON�ַ���
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
	}
}
