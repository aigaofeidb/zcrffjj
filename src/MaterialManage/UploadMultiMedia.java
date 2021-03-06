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
		// 拼接请求地址
		String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		uploadMediaUrl.replace("ACCESS_TOKEN", accessToken);
		uploadMediaUrl.replace("TYPE", type);
		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");

			// 设置请求头 Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary" + boundary);

			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();
			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
			mediaConn.setDoOutput(true);
			mediaConn.setRequestMethod("GET");

			// 从请求头中 获取内容类型
			String contentType = mediaConn.getHeaderField("Content-Type");
			// 根据内容类型判断文件扩展名
			String fileExt = CommonUtil.getFileExt(contentType);

			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(
					String.format("Content-Disposition: form-data; ncame=\"media\"; filename=\"file1%s\"\r\n", fileExt)
							.getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件的输入流（读入文件）
			BufferedInputStream bis = new BufferedInputStream(mediaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到 输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}

			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			mediaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
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
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 使用JSON-lib 解析返回结果
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println(jsonObject);
			weixinMedia = new WeixinMedia();
			weixinMedia.setType(jsonObject.getString("type"));

			// type 等于thumb 时返回结果和其他类型不一样。
			if ("thumb".equals(type)) {
				weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
			} else {
				weixinMedia.setMediaId(jsonObject.getString("media_id"));
				weixinMedia.setCreateAt(jsonObject.getInt("created_at"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			weixinMedia = null;
			log.error("上传媒体文件失败：{}", e);
		}
		return weixinMedia;
	}
}
