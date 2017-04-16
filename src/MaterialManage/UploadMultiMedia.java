package MaterialManage;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.log.Log;

import net.sf.json.JSONObject;
import util.CommonUtil;

public class UploadMultiMedia {

	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	public static WeixinMedia uploadMedia(String accessToken, String type, String mediaFileUrl) {
		WeixinMedia weixinMedia = null;
		// ƴ�������ַ
		String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		uploadMediaUrl.replace("ACCESS_TOKEN", accessToken);
		uploadMediaUrl.replace("TYPE", type);
		// �������ݷָ���
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");

			// ��������ͷ Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary" + boundary);

			// ��ȡý���ļ��ϴ������������΢�ŷ�����д���ݣ�
			OutputStream outputStream = uploadConn.getOutputStream();
			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
			mediaConn.setDoOutput(true);
			mediaConn.setRequestMethod("GET");

			// ������ͷ�� ��ȡ��������
			String contentType = mediaConn.getHeaderField("Content-Type");
			// �������������ж��ļ���չ��
			String fileExt = CommonUtil.getFileExt(contentType);

			// �����忪ʼ
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(
					String.format("Content-Disposition: form-data; ncame=\"media\"; filename=\"file1%s\"\r\n", fileExt)
							.getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// ��ȡý���ļ����������������ļ���
			BufferedInputStream bis = new BufferedInputStream(mediaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// ��ý���ļ�д�� ���������΢�ŷ�����д���ݣ�
				outputStream.write(buf, 0, size);
			}

			// ���������
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			mediaConn.disconnect();

			// ��ȡý���ļ��ϴ�������������΢�ŷ����������ݣ�
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// ʹ��JSON-lib �������ؽ��
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println(jsonObject);
			weixinMedia = new WeixinMedia();
			weixinMedia.setType(jsonObject.getString("type"));

			// type ����thumb ʱ���ؽ�����������Ͳ�һ����
			if ("thumb".equals(type)) {
				weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
			} else {
				weixinMedia.setMediaId(jsonObject.getString("media_id"));
				weixinMedia.setCreateAt(jsonObject.getInt("created_at"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			weixinMedia = null;
			log.error("�ϴ�ý���ļ�ʧ�ܣ�{}", e);
		}
		return weixinMedia;
	}
}
