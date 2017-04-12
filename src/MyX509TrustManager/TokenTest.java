package MyX509TrustManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

//���Ի�ȡƾ֤
public class TokenTest {
	public static void main(String[] args)
	throws Exception{
		//��ȡƾ֤�ӿڵ�ַ
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=0000000000000000&secret=111111111";
		//��������
		URL url = new URL(tokenUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//ʹ���Զ�������ι�����
		TrustManager[] tm = { new MyX509TrustManager()};
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoInput(true);
		//��������ʽ
		conn.setRequestMethod("GET");
		//ȡ��������
		InputStream  inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		//��ȡ��Ӧ����
		StringBuffer buffer = new StringBuffer();
		String str = null;
		
		while ( (str = bufferedReader.readLine()) != null)
		{
			buffer.append(str);
		}
		// �ر� / �ͷ���Դ
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();
		//������ؽ��
		System.out.println(buffer);
	}
}
