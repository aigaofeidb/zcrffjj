<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="gb2312"%>		<!-- �� gb2312 �滻 ISO-8859-1  -->
  
<%@ page import="pojo.SNSUserInfo;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>OAuth2.0��ҳ��Ȩ</title>
<meta name="viewport" http-equiv="Content-Type" content="width=device-width,user-scalable=0">
<style type="text/css">
	*{margin:0;padding:0}
	table{border:1px dashed #B9B9DD; font-size:12pt}
	td{border:1px dashed #B9B9DD;word-break:break-all;word-wrap:break-word;}
</style>
</head>
<body>
	<%
	//��ȡ��OAuthServlet����Ĳ���
	SNSUserInfo userInfo = (SNSUserInfo)request.getAttribute("snsUserInfo");
	if(null != userInfo)
	{
	%>
	<table width="100%" cellspacing="0" cellpadding="0">
	<tr><td width="20%">����</td><td width="80%">ֵ</td></tr>
	<tr><td>openId</td><td><%=userInfo.getOpenId()%></td></tr>
	<tr><td>�ǳ�</td><td><%=userInfo.getNickname()%></td></tr>
	<tr><td>�Ա�</td><td><%=userInfo.getSex()%></td></tr>
	<tr><td>����</td><td><%=userInfo.getCountry()%></td></tr>
	<tr><td>ʡ��</td><td><%=userInfo.getProvince()%></td></tr>
	<tr><td>����</td><td><%=userInfo.getCity()%></td></tr>
	<tr><td>ͷ��</td><td><%=userInfo.getHeadImgUrl()%></td></tr>
	<tr><td>��Ȩ</td><td><%=userInfo.getPrivilegeList()%></td></tr>
	</table>
	<%
	}
	else
		out.print("δ��ȡ���û���Ϣ");
	%>
</body>
</html>