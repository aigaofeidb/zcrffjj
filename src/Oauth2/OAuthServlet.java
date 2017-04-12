package Oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.SNSUserInfo;
import pojo.WeixinOauth2Token;

//	授权后的回调请求处理
public class OAuthServlet extends HttpServlet{
	private static final long serialVersionUID = -1191919191919L;
	 public void doGet(HttpServletRequest request,HttpServletResponse response)
	 throws ServletException, IOException{
		 //这里的gb2312是什么意思？？
		 request.setCharacterEncoding("gb2312");
		 response.setCharacterEncoding("gb2312");
		 
		 //用户同意授权后，获取到code
		 String code = request.getParameter("code");
		 
		 //用户同意授权后
		 if (!"authdeny".equals(code)) {
			//获取网页授权 access_token
			 WeixinOauth2Token weixinOauth2Token = Oauth2.getOauth2AccessToken("APPID","APPSECRET",code);
			 //网页授权接口 访问凭证
			 String accessToken = weixinOauth2Token.getAccessToken();
			// 获取用户标识 
			 String openId = weixinOauth2Token.getOpenId();
			 SNSUserInfo snsUserInfo = Oauth2.getSNSUserInfo(accessToken, openId);
			 //设置要传输的参数
			 request.setAttribute("snsUserInfo", snsUserInfo);
		}
		 // 跳转到 index.jsp
		 request.getRequestDispatcher("index.jsp").forward(request, response);
	 }
}
