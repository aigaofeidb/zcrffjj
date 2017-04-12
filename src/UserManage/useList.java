package UserManage;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.CommonUtil;

public class useList {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	// 获取用户信息
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static WeixinUserList getUserList(String accessToken,
			String nextOpenId) {
		WeixinUserList weixinUserList = null;
		if (null == nextOpenId) {
			nextOpenId = "";
		}

		// 拼接请求地址：
		String requetUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		requetUrl.replace("ACCESS_TOKEN", accessToken);
		requetUrl.replace("NEXT_OPENID", nextOpenId);
		JSONObject jsonObject = CommonUtil.httpsRequest(accessToken, "GET",
				null);

		if (null != jsonObject) {
			try {
				weixinUserList = new WeixinUserList();
				weixinUserList.setTotal(jsonObject.getInt(""));
				weixinUserList.setCount(jsonObject.getInt(""));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
/*				weixinUserList.setOpenIdList(JSONArray.toList(
						dataObject.getJSONArray("openid"), List.class));*/
				//这里不再采用 tolist方法（因为过时了），采取替代方法toCollectionn
				weixinUserList.setOpenIdList((List<String>)JSONArray.toCollection(dataObject.getJSONArray("openid"), List.class));
				weixinUserList.setNextopenid(jsonObject.getString(""));
			} catch (Exception e) {
				// TODO: handle exception
				weixinUserList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errMsg = jsonObject.getString("errmsg");
				log.error("获取关注者列表失败！errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}

		return weixinUserList;
	}
}
