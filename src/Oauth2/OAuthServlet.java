package Oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;

//	��Ȩ��Ļص�������
public class OAuthServlet extends HttpServlet{
	private static final long serialVersionUID = -1191919191919L;
	 public void doGet(HttpServletRequest request,HttpServletResponse response)
	 throws ServletException, IOException{
		 //�����gb2312��ʲô��˼����
		 request.setCharacterEncoding("gb2312");
		 response.setCharacterEncoding("gb2312");
		 
		 //�û�ͬ����Ȩ�󣬻�ȡ��code
		 String code = request.getParameter("code");
		 
		 //�û�ͬ����Ȩ��
		 if (!"authdeny".equals(code)) {
			//��ȡ��ҳ��Ȩ access_token
			 WeixinOauth2Token weixinOauth2Token = Oauth2.getOauth2AccessToken("APPID","APPSECRET",code);
			 //��ҳ��Ȩ�ӿ� ����ƾ֤
			 String accessToken = weixinOauth2Token.getAccessToken();
			// ��ȡ�û���ʶ 
			 String openId = weixinOauth2Token.getOpenId();
			 SNSUserInfo snsUserInfo = Oauth2.getSNSUserInfo(accessToken, openId);
			 //����Ҫ����Ĳ���
			 request.setAttribute("snsUserInfo", snsUserInfo);
		}
		 // ��ת�� index.jsp
		 request.getRequestDispatcher("index.jsp").forward(request, response);
	 }
}
