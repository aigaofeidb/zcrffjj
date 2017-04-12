package MyX509TrustManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

//测试获取凭证
public class TokenTest {
	public static void main(String[] args)
	throws Exception{
		//获取凭证接口地址
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=0000000000000000&secret=111111111";
		//建立连接
		URL url = new URL(tokenUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//使用自定义的信任管理器
		TrustManager[] tm = { new MyX509TrustManager()};
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoInput(true);
		//设置请求方式
		conn.setRequestMethod("GET");
		//取得输入流
		InputStream  inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		//读取响应内容
		StringBuffer buffer = new StringBuffer();
		String str = null;
		
		while ( (str = bufferedReader.readLine()) != null)
		{
			buffer.append(str);
		}
		// 关闭 / 释放资源
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();
		//输出返回结果
		System.out.println(buffer);
	}
}
