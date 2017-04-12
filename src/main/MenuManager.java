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
		btn11.setName("开源中国");
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
		btn21.setName("淘宝");
		btn21.setType("view");
		btn21.setUrl("http://m.taobao.com");

		ViewButton btn22 = new ViewButton();
		btn22.setName("京东");
		btn22.setType("view");
		btn22.setUrl("http://m.jd.com");

		ViewButton btn23 = new ViewButton();
		btn23.setName("唯品会");
		btn23.setType("view");
		btn23.setUrl("http://m.vipshop.com");

		ViewButton btn24 = new ViewButton();
		btn24.setName("当当网");
		btn24.setType("view");
		btn24.setUrl("http://m.dangdang.com");

		ViewButton btn25 = new ViewButton();
		btn25.setName("苏宁易购");
		btn25.setType("view");
		btn25.setUrl("http://m.suning.com");

		ViewButton btn31 = new ViewButton();
		btn31.setName("多泡");
		btn31.setType("view");
		btn31.setUrl("http://www.duopao.com");

		ViewButton btn32 = new ViewButton();
		btn32.setName("一窝88");
		btn32.setType("view");
		btn32.setUrl("http://www.yi588.com");

		Complexbutton mainBtn1 = new Complexbutton();
		mainBtn1.setName("技术交流");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

		Complexbutton mainBtn2 = new Complexbutton();
		mainBtn1.setName("购物");
		mainBtn1.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });

		Complexbutton mainBtn3 = new Complexbutton();
		mainBtn1.setName("网页游戏");
		mainBtn1.setSub_button(new Button[] { btn31, btn32 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxb2b3259d633d3587";
		// 第三方用户唯一凭证密钥
		String appSecret = "915386d1094aa397cf9cc276e3a3f1b1";
		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(),
					token.getAccessToken());
			if (result) {
				log.info("菜单创建成功！");
			} else {
				log.info("菜单创建失败！");
			}
		}

	}

}
