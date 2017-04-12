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

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		requestUrl.replace("TOKEN", accessToken);

		// 需要提交的JSON数据
		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";

		// 创建临时带参数二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, expireSeconds, sceneId));

		if (null != jsonObject) {
			try {
				weixinQRCode = new WeixinQRCode();
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObject
						.getInt("expire_seconds"));
				log.info("创建临时带参二维码成功 ticket:{} expire_seconds:{}",
						weixinQRCode.getTicket(),
						weixinQRCode.getExpireSeconds());
			} catch (Exception e) {
				// TODO: handle exception
				weixinQRCode = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建临时带参二维码失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return weixinQRCode;

	}

	public static String createPermanentQRCode(String accessToken,
			int expireSeconds, int sceneId) {
		String ticket = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		requestUrl.replace("TOKEN", accessToken);

		// 需要提交的JSON数据
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";

		// 创建永久带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, sceneId));
		
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				log.info("创建永久带参二维码成功 ticket:{}",ticket);
			} catch (Exception e) {
				// TODO: handle exception
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建永久带参二维码失败 errcode:{} errmsg:{}",errorCode,errorMsg);
			}
		}
		return ticket;
	}
	//通过ticket换取二维码图片
	public static String getQRCode(String ticket,String savePath){
		
		String filePath = null;
		
		// 拼接请求地址
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
			
			//将ticket作为文件名
			filePath = savePath + ticket + ".jpg";
			//将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(coon.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ( (size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
				fos.close();
				bis.close();
				coon.disconnect();
				log.info("根据ticket换取二维码成功，filePath=" + filePath);	
			
		} catch (Exception e) {
			// TODO: handle exception
			filePath = null;
			log.error("根据ticket换取二维码失败：{}" ,e);
		}
		return filePath;
	}
}
