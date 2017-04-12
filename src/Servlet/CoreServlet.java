package Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import util.MessageUtil;
import util.SignUtil;

/**
 * Servlet implementation class CoreServlet
 */
@WebServlet("/CoreServlet")
public class CoreServlet extends HttpServlet {
	// ��֪����� UID ��ʲô��˼
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * Default constructor.
	 */
	public CoreServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// ����У�飬ȷ����������΢�ŷ�����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ΢�ż���ǩ��
		String signature = request.getParameter("signature");
		// ʱ���
		String timestamp = request.getParameter("timestamp");
		// �����
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();

		// ����У�飬��У��ɹ�����ԭ������echostr����ʾ����ɹ����������ʧ��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * ���� ΢�ŷ�������������Ϣ/����У���봦��
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �� ������Ӧ�ı��붼���ó� UTF-8����ֹ��������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// ���ղ�����΢�ż���ǩ����ʱ����������
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		PrintWriter out = response.getWriter();

		// ����У��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			// ���ú��ķ�������մ�������
			String respXml = CoreService.processRequest(request);
			out.print(respXml);
		}
		out.close();
		out = null;
	}

}
