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

		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

		// ��ѯ����
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				/*
				 * weixinUserList.setOpenIdList(JSONArray.toList(
				 * dataObject.getJSONArray("openid"), List.class));
				 */
				// ���ﲻ�ٲ��� tolist��������Ϊ��ʱ�ˣ�����ȡ�������toCollectionn
				weixinTagsList = (List<WeixinTag>) JSONArray.toCollection(
						jsonObject.getJSONArray("tag"), WeixinTag.class);
			} catch (Exception e) {
				weixinTagsList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errMsg = jsonObject.getString("errmsg");
				log.error("��ѯ��ǩʧ�ܣ�errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}

		return weixinTagsList;
	}

	public static WeixinTag creeteTags(String accessToken, String groupName) {
		WeixinTag weixinTag = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// ��Ҫ�ύ��JSON����
		String jsonData = "{\"tag\" : {\"name\" : \"%s\"}}";
		// ������ǩ
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
				log.error("������ǩʧ�ܣ�errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}
		return weixinTag;
	}

	public static boolean updateTag(String accessToken, int groupId,
			String groupName) {
		// ���� ���༭��ǩ��
		boolean result = false;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// ��Ҫ�ύ��JSON����
		String jsonData = "{\"tag\" : {\"id\" : %d,\"name\" : \"%s\"}}";
		// �޸ı�ǩ
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupId, groupName));
		// �޸ĵı�ǩ��
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("�޸ı�ǩ�ɹ���errcode:{},errmsg:{}", errorCode, errMsg);
			} else {
				log.error("�޸ı�ǩʧ�ܣ�errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}
		return result;
	}

	public static boolean deleteTag(String accessToken, int groupId,
			String groupName) {
		// ���� ���༭��ǩ��
		boolean result = false;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// ��Ҫ�ύ��JSON����
		String jsonData = "{\"tag\" : {\"id\" : %d,\"name\" : \"%s\"}}";
		// �޸ı�ǩ
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupId, groupName));
		// �޸ĵı�ǩ��
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("�޸ı�ǩ�ɹ���errcode:{},errmsg:{}", errorCode, errMsg);
			} else {
				log.error("�޸ı�ǩʧ�ܣ�errcode:{},errmsg:{}", errorCode, errMsg);
			}
		}
		return result;
	}
}
