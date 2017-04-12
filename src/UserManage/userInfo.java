package UserManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.CommonUtil;
import net.sf.json.JSONObject;

public class userInfo {
	
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	// 获取用户信息
	public static WeixinUserInfo getUserInfo(String accessToken, String OpenId) {
		WeixinUserInfo weixinUserInfo = null;
		// 拼接请求地址
		String requetUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requetUrl.replace("ACCESS_TOKEN", accessToken);
		requetUrl.replace("OPENID", OpenId);
		JSONObject jsonObject = CommonUtil.httpsRequest(accessToken, "GET",
				null);

		if (null != jsonObject) {
			try {
				//为什么这里声明了两次？？
				weixinUserInfo = new WeixinUserInfo();
				// 用户标识
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
				//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
				if (0 == weixinUserInfo.getSubscribe()) {
					log.error("用户 {} 已取消关注",weixinUserInfo.getOpenId());
				}else {
					int errorCode = jsonObject.getInt("errcode");
					String errMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败： errorcode:{} errormsg:{}",errorCode,errMsg);
				}
			}
		}

		return weixinUserInfo;
	}
}
