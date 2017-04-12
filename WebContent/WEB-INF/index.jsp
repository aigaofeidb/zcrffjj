<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="gb2312"%>		<!-- 用 gb2312 替换 ISO-8859-1  -->
  
<%@ page import="pojo.SNSUserInfo;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>OAuth2.0网页授权</title>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width,user-scalable=0">
<style type="text/css">
	*{margin:0;padding:0}
	table{border:1px dashed #B9B9DD; font-size:12pt}
	td{border:1px dashed #B9B9DD;word-break:break-all;word-wrap:break-word;}
</style>
</head>
<body>
	<%
	//获取由OAuthServlet传入的参数
	SNSUserInfo userInfo = (SNSUserInfo)request.getAttribute("snsUserInfo");
	if(null != userInfo)
	{
	%>
	<table width="100%" cellspacing="0" cellpadding="0">
	<tr><td width="20%">属性</td><td width="80%">值</td></tr>
	<tr><td>openId</td><td><%=userInfo.getOpenId()%></td></tr>
	<tr><td>昵称</td><td><%=userInfo.getNickname()%></td></tr>
	<tr><td>性别</td><td><%=userInfo.getSex()%></td></tr>
	<tr><td>国家</td><td><%=userInfo.getCountry()%></td></tr>
	<tr><td>省份</td><td><%=userInfo.getProvince()%></td></tr>
	<tr><td>城市</td><td><%=userInfo.getCity()%></td></tr>
	<tr><td>头像</td><td><%=userInfo.getHeadImgUrl()%></td></tr>
	<tr><td>特权</td><td><%=userInfo.getPrivilegeList()%></td></tr>
	</table>
	<%
	}
	else
		out.print("未获取到用户信息");
	%>
</body>
</html>