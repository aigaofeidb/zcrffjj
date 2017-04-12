package MenuBtn;

import net.sf.json.JSONObject;

//import com.baidu.bdt.java.util.json.JSONObject;

public class CreateMenu {
	public static void main(String[] args) {
		ClickButton btn1 = new ClickButton();
		btn1.setName("今日歌曲");
		btn1.setType("click");
		btn1.setKey("V1001_TODAY_MUSIC");

		ViewButton btn2 = new ViewButton();
		btn2.setName("歌手简介");
		btn2.setType("view");
		btn2.setUrl("http://www.qq.com");

		ClickButton btn31 = new ClickButton();
		btn31.setName("hello world");
		btn31.setType("click");
		btn31.setKey("V1001_HELLO_WORLD");

		ClickButton btn32 = new ClickButton();
		btn32.setName("赞我们一下");
		btn32.setType("click");
		btn32.setKey("V1001_GOOD");

		// 复合按钮包含2个click类型的按钮
		Complexbutton btn3 = new Complexbutton();
		btn3.setName("菜单");
		btn3.setSub_button(new Button[] { btn31, btn32 });

		// 创建菜单对象
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn1, btn2, btn3 });

		// 将菜单对象转换为JSON字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
	}
}
