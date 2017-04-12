package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Token;
import util.CommonUtil;
import util.MenuUtil;

import MenuBtn.Button;
import MenuBtn.ClickButton;
import MenuBtn.Complexbutton;
import MenuBtn.Menu;
import MenuBtn.ViewButton;

public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	private static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("��Դ�й�");
		btn11.setType("click");
		btn11.setKey("oschina");

		ClickButton btn12 = new ClickButton();
		btn12.setName("ITeye");
		btn12.setType("click");
		btn12.setKey("iteye");

		ViewButton btn13 = new ViewButton();
		btn13.setName("CocoaChina");
		btn13.setType("view");
		btn13.setUrl("http://www.iteye.com");

		ViewButton btn21 = new ViewButton();
		btn21.setName("�Ա�");
		btn21.setType("view");
		btn21.setUrl("http://m.taobao.com");

		ViewButton btn22 = new ViewButton();
		btn22.setName("����");
		btn22.setType("view");
		btn22.setUrl("http://m.jd.com");

		ViewButton btn23 = new ViewButton();
		btn23.setName("ΨƷ��");
		btn23.setType("view");
		btn23.setUrl("http://m.vipshop.com");

		ViewButton btn24 = new ViewButton();
		btn24.setName("������");
		btn24.setType("view");
		btn24.setUrl("http://m.dangdang.com");

		ViewButton btn25 = new ViewButton();
		btn25.setName("�����׹�");
		btn25.setType("view");
		btn25.setUrl("http://m.suning.com");

		ViewButton btn31 = new ViewButton();
		btn31.setName("����");
		btn31.setType("view");
		btn31.setUrl("http://www.duopao.com");

		ViewButton btn32 = new ViewButton();
		btn32.setName("һ��88");
		btn32.setType("view");
		btn32.setUrl("http://www.yi588.com");

		Complexbutton mainBtn1 = new Complexbutton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

		Complexbutton mainBtn2 = new Complexbutton();
		mainBtn1.setName("����");
		mainBtn1.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });

		Complexbutton mainBtn3 = new Complexbutton();
		mainBtn1.setName("��ҳ��Ϸ");
		mainBtn1.setSub_button(new Button[] { btn31, btn32 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wxb2b3259d633d3587";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "915386d1094aa397cf9cc276e3a3f1b1";
		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// �����˵�
			boolean result = MenuUtil.createMenu(getMenu(),
					token.getAccessToken());
			if (result) {
				log.info("�˵������ɹ���");
			} else {
				log.info("�˵�����ʧ�ܣ�");
			}
		}

	}

}
