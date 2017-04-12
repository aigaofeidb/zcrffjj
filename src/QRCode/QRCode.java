package QRCode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.eclipse.osgi.framework.adaptor.FilePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Encode.urlEncode;
import util.CommonUtil;
import net.sf.json.JSONObject;

public class QRCode {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	public static WeixinQRCode createTemporaryQRCode(String accessToken,
			int expireSeconds, int sceneId) {
		WeixinQRCode weixinQRCode = null;

		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		requestUrl.replace("TOKEN", accessToken);

		// ��Ҫ�ύ��JSON����
		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";

		// ������ʱ��������ά��
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, expireSeconds, sceneId));

		if (null != jsonObject) {
			try {
				weixinQRCode = new WeixinQRCode();
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObject
						.getInt("expire_seconds"));
				log.info("������ʱ���ζ�ά��ɹ� ticket:{} expire_seconds:{}",
						weixinQRCode.getTicket(),
						weixinQRCode.getExpireSeconds());
			} catch (Exception e) {
				// TODO: handle exception
				weixinQRCode = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("������ʱ���ζ�ά��ʧ�� errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return weixinQRCode;

	}

	public static String createPermanentQRCode(String accessToken,
			int expireSeconds, int sceneId) {
		String ticket = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		requestUrl.replace("TOKEN", accessToken);

		// ��Ҫ�ύ��JSON����
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";

		// �������ô��ζ�ά��
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, sceneId));
		
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				log.info("�������ô��ζ�ά��ɹ� ticket:{}",ticket);
			} catch (Exception e) {
				// TODO: handle exception
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("�������ô��ζ�ά��ʧ�� errcode:{} errmsg:{}",errorCode,errorMsg);
			}
		}
		return ticket;
	}
	//ͨ��ticket��ȡ��ά��ͼƬ
	public static String getQRCode(String ticket,String savePath){
		
		String filePath = null;
		
		// ƴ�������ַ
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl.replace("TICKET", urlEncode.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection coon =  (HttpsURLConnection)url.openConnection();
			coon.setDoInput(true);
			coon.setRequestMethod("GET");
			if(!savePath.endsWith("/"))
			{
				savePath += "/";
			}
			
			//��ticket��Ϊ�ļ���
			filePath = savePath + ticket + ".jpg";
			//��΢�ŷ��������ص�������д���ļ�
			BufferedInputStream bis = new BufferedInputStream(coon.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ( (size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
				fos.close();
				bis.close();
				coon.disconnect();
				log.info("����ticket��ȡ��ά��ɹ���filePath=" + filePath);	
			
		} catch (Exception e) {
			// TODO: handle exception
			filePath = null;
			log.error("����ticket��ȡ��ά��ʧ�ܣ�{}" ,e);
		}
		return filePath;
	}
}
