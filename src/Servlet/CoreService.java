package Servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ReMessage.Article;
import ReMessage.NewsMessage;
import ReMessage.TextMessage;

import util.MessageUtil;

public class CoreService {

	public static String processRequest(HttpServletRequest request) {
		// XML ��ʽ����Ϣ����
		String respXml = null;
		// Ĭ�Ϸ��ص� �ı���Ϣ����
		String respContent = "δ֪����Ϣ����!";

		try {
			// ͨ�� parseXml ���� ���� XML ����
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��˺�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				respContent = "���͵����ı���Ϣ��";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "���͵���ͼƬ��Ϣ��";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "���͵�����Ƶ��Ϣ��";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "���͵�����Ƶ��Ϣ��";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "���͵��ǵ���λ����Ϣ��";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "���͵���������Ϣ��";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ���� �¼�
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("�ǳ���л����ע�ɷɽ��˵���£�");
					respXml = MessageUtil.messageToXml(textMessage);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// ȡ����ע�󣬲����յ���Ϣ��
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// ����ɨ��������Ķ�ά���¼�
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// �ϱ�����λ���¼�
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���˵�ʱ�� keyֵ��Ӧ
					String eventKey = requestMap.get("EventKey");
					// ����keyֵ���ж��û�����İ�ť��Ȼ�������Ӧ�Ĳ���
					if (eventKey.equals("oschina")) {
						Article article = new Article();
						article.setTitle("�ɷɽ��");
						article.setDescription("����");
						article.setPicUrl("");
						article.setUrl("http://www.feifeijiejie.com");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);

						// ����ͼ����Ϣ
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage
								.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("iteye")) {
						textMessage.setContent("�ڶ�����ť");
						respXml = MessageUtil.messageToXml(textMessage);
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return respXml;
	}
}
