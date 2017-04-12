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
		// XML 格式的消息数据
		String respXml = null;
		// 默认返回的 文本消息内容
		String respContent = "未知的消息类型!";

		try {
			// 通过 parseXml 方法 解析 XML 内容
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方账号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				respContent = "发送的是文本消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "发送的是图片消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "发送的是音频消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "发送的是视频消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "发送的是地理位置消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "发送的是链接消息！";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅 事件
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("非常感谢您关注飞飞姐姐说故事！");
					respXml = MessageUtil.messageToXml(textMessage);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消关注后，不会收到消息了
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// 处理扫描带参数的二维码事件
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// 上报地理位置事件
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的 key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值，判断用户点击的按钮，然后进行相应的操作
					if (eventKey.equals("oschina")) {
						Article article = new Article();
						article.setTitle("飞飞姐姐");
						article.setDescription("哈哈");
						article.setPicUrl("");
						article.setUrl("http://www.feifeijiejie.com");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);

						// 创建图文消息
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
						textMessage.setContent("第二个按钮");
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
