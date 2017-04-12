package util;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import MenuBtn.Menu;

public class MenuUtil {
	private static Logger log = LoggerFactory.getLogger(MenuUtil.class);

	// �˵�����(POST)
	public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// �˵���ѯ(GET)
	public final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	// �˵�ɾ��(GET)
	public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/*
	 * �˵�����
	 */
	public static boolean createMenu(Menu menu, String accessToken) {
		boolean result = false;

		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// ���˵�����ת����JSON�ַ���
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// ����POST���󣬴����˵�
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);
		if (null != jsonObject) {
			int errorcode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorcode) {
				result = true;
			} else {
				result = false;
				log.error("�����˵�ʧ�� errcode:{} errmsg:{}", errorcode, errorMsg);
			}
		}
		return result;
	}

	/*
	 * ��ѯ�˵�
	 */
	public static String getMenu(String accessToken) {
		String result = null;
		String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		// ����GET�����ѯ�˵�
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			result = jsonObject.toString();
		}

		return result;
	}

	/*
	 * �˵�ɾ��
	 */

	public static boolean deleteMenu(String accessToken) {
		boolean result = false;
		String requestUrl = menu_delete_url
				.replace("ACCESS_TOKEN", accessToken);
		// ����ɾ��GET����
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				log.error("ɾ���˵�ʧ�� errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}

}
