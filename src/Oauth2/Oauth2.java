package Oauth2;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.CommonUtil;

public class Oauth2 {
	
	// 不理解
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	//获取 AccessToken
	public static WeixinOauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);

		// 获取网页授权凭证
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("openid"));
			} catch (Exception e) {
				// TODO: handle exception
				wat = null;
				int errorcode = jsonObject.getInt("errorcode");
				String errorMsg = jsonObject.getString("errormsg");
				log.error("获取网页凭证失败 errorcode:{} errormsg:{}", errorcode,
						errorMsg);
			}
		}
		return wat;
	}
	//刷新 AccessToken
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,
			String refreshToken) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址

		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// 刷新网页授权
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				// TODO: handle exception
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("刷新网页授权凭证失败 errorcode:{} errormsg:{}", errorCode,
						errorMsg);
			}
		}
		return wat;
	}
	//拉取用户信息
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static SNSUserInfo getSNSUserInfo(String accessToken,
			String openId) {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址

		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		// 刷新网页授权
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				snsUserInfo.setCountry(jsonObject.getString("country"));
				snsUserInfo.setProvince(jsonObject.getString("province"));
				
				snsUserInfo.setCity(jsonObject.getString("city"));
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headImgUrl"));
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				// TODO: handle exception
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errorcode:{} errormsg:{}", errorCode,
						errorMsg);
			}
		}
		return snsUserInfo;
	}
	
	
	
}
