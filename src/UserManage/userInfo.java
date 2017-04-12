package UserManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.CommonUtil;
import net.sf.json.JSONObject;

public class userInfo {
	
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	// ��ȡ�û���Ϣ
	public static WeixinUserInfo getUserInfo(String accessToken, String OpenId) {
		WeixinUserInfo weixinUserInfo = null;
		// ƴ�������ַ
		String requetUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requetUrl.replace("ACCESS_TOKEN", accessToken);
		requetUrl.replace("OPENID", OpenId);
		JSONObject jsonObject = CommonUtil.httpsRequest(accessToken, "GET",
				null);

		if (null != jsonObject) {
			try {
				//Ϊʲô�������������Σ���
				weixinUserInfo = new WeixinUserInfo();
				// �û���ʶ
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				weixinUserInfo.setCity(jsonObject.getString("city"));
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
			} catch (Exception e) {
				// TODO: handle exception
				//�û��Ƿ��ĸù��ںű�ʶ��ֵΪ0ʱ��������û�û�й�ע�ù��ںţ���ȡ����������Ϣ��
				if (0 == weixinUserInfo.getSubscribe()) {
					log.error("�û� {} ��ȡ����ע",weixinUserInfo.getOpenId());
				}else {
					int errorCode = jsonObject.getInt("errcode");
					String errMsg = jsonObject.getString("errmsg");
					log.error("��ȡ�û���Ϣʧ�ܣ� errorcode:{} errormsg:{}",errorCode,errMsg);
				}
			}
		}

		return weixinUserInfo;
	}
}
