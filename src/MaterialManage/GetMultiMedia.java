package MaterialManage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.CommonUtil;

public class GetMultiMedia {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	public static String getMedia(String accessToken, String mediaId, String savePath) {

		String filePath = null;
		// ƴ�������ַ
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);

		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}

			// �����������ͻ�ȡ��չ��
			String fileExt = CommonUtil.getFileExt(connection.getHeaderField("Content-Type"));
			// Media ID ���ļ���
			filePath = savePath + mediaId + fileExt;
			BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();

			connection.disconnect();
			log.info("����ý���ļ��ɹ���filePath=" + filePath);

		} catch (Exception e) {
			// TODO: handle exception
			filePath = null;
			log.error("����ý���ļ�ʧ��:{}", e);
		}

		return filePath;
	}

}
