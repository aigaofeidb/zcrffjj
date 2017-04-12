package UserManage;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import util.CommonUtil;

public class userTags {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	@SuppressWarnings("unchecked")
	public static List<WeixinTag> getTags(String accessToken) {
		List<WeixinTag> weixinTagsList = null;

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

		// 查询分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				/*
				 * weixinUserList.setOpenIdList(JSONArray.toList(
				 * dataObject.getJSONArray("openid"), List.class));
				 */
				// 这里不再采用 tolist方法（因为过时了），采取替代方法toCollectionn
				weixinTagsList = (List<WeixinTag>) JSONArray.toCollection(
						jsonObject.getJSONArray("tag"), WeixinTag.class);
			} catch (Exception e) {
				weixinTagsList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errMsg = jsonObject.getString("errmsg");
				log.error("查询标签失败！errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}

		return weixinTagsList;
	}

	public static WeixinTag creeteTags(String accessToken, String groupName) {
		WeixinTag weixinTag = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的JSON数据
		String jsonData = "{\"tag\" : {\"name\" : \"%s\"}}";
		// 创建标签
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupName));
		if (null != jsonObject) {
			try {
				weixinTag = new WeixinTag();
				weixinTag.setId(jsonObject.getJSONObject("tag").getInt("id"));
				weixinTag.setName(jsonObject.getJSONObject("tag").getString(
						"name"));
			} catch (JSONException e) {
				weixinTag = null;
				int errorCode = jsonObject.getInt("errcode");
				String errMsg = jsonObject.getString("errmsg");
				log.error("创建标签失败！errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}
		return weixinTag;
	}

	public static boolean updateTag(String accessToken, int groupId,
			String groupName) {
		// 即是 “编辑标签”
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的JSON数据
		String jsonData = "{\"tag\" : {\"id\" : %d,\"name\" : \"%s\"}}";
		// 修改标签
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupId, groupName));
		// 修改的标签名
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("修改标签成功！errcode:{},errmsg:{}", errorCode, errMsg);
			} else {
				log.error("修改标签失败！errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}
		return result;
	}

	public static boolean deleteTag(String accessToken, int groupId,
			String groupName) {
		// 即是 “编辑标签”
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的JSON数据
		String jsonData = "{\"tag\" : {\"id\" : %d,\"name\" : \"%s\"}}";
		// 修改标签
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupId, groupName));
		// 修改的标签名
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("修改标签成功！errcode:{},errmsg:{}", errorCode, errMsg);
			} else {
				log.error("修改标签失败！errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}
		return result;
	}
}
